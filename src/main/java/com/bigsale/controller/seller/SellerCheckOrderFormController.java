package com.bigsale.controller.seller;

import com.bigsale.controller.dto.CheckOrderDto;
import com.bigsale.orm.model.DeliveryStatus;
import com.bigsale.orm.model.Item;
import com.bigsale.orm.model.ItemOrder;
import com.bigsale.orm.model.Seller;
import com.bigsale.service.ItemOrderService;
import com.bigsale.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Seller: hanmoi
 * Date: 7/10/12
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller("sellerCheckOrderFormController")
@RequestMapping("/seller/checkOrderForm")
public class SellerCheckOrderFormController {
    public static final String CHECK_ORDER_FORM = "/seller/checkOrderResultForm";
    static final Logger logger = LoggerFactory.getLogger(SellerCheckOrderFormController.class);

    private Set<CheckOrderDto> ordersOnProcess = new HashSet<CheckOrderDto>();
    private Set<CheckOrderDto> ordersDelivered = new HashSet<CheckOrderDto>();

    @Autowired
    SellerService sellerService;

    @Autowired
    ItemOrderService itemOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request, HttpServletResponse response,
                            SessionStatus status,
                            Model model)
    {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerService.getSellerById(sellerId);

        ordersDelivered.clear();
        ordersOnProcess.clear();

        Set<Item> items = seller.getItems();

        Iterator<Item> iterator = items.iterator();

        logger.debug("size of item : {}", items.size());
        while(iterator.hasNext()){
            Item item = iterator.next();

            Set<ItemOrder> itemOrders = item.getItemOrders();
            logger.debug("size of item order : {}", itemOrders.size());

            Iterator<ItemOrder> itemOrderIterator = itemOrders.iterator();
            while (itemOrderIterator.hasNext()){
                ItemOrder itemOrder = itemOrderIterator.next();

                CheckOrderDto dto = new CheckOrderDto();
                dto.setOrderId(itemOrder.getItemOrderId());
                dto.setItemName(itemOrder.getItem().getItemName());
                dto.setOrderAmount(itemOrder.getOrderQuantity());

                if(itemOrder.getDeliveryStatus() == DeliveryStatus.DELIVERED){
                    dto.setDeliveryStatus(DeliveryStatus.DELIVERED.toString().toUpperCase());
                    ordersDelivered.add(dto);
                }
                else{
                    dto.setDeliveryStatus(DeliveryStatus.PREPARING.toString().toUpperCase());
                    ordersOnProcess.add(dto);
                }
            }
        }

        model.addAttribute("ordersOnProcess", ordersOnProcess);
        model.addAttribute("ordersDelivered", ordersDelivered);

        return CHECK_ORDER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateDeliveryStatus(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("orderId") int orderId,
            SessionStatus status,Model model){

        logger.debug("order ID: {}", orderId);

        updateItemOrder(orderId);

        updateItemOrderDto(orderId);

        model.addAttribute("ordersOnProcess", ordersOnProcess);
        model.addAttribute("ordersDelivered", ordersDelivered);

        return CHECK_ORDER_FORM;
    }

    private void updateItemOrderDto(int orderId)
    {
        Iterator<CheckOrderDto> iterator = ordersOnProcess.iterator();
        while (iterator.hasNext()){
            CheckOrderDto checkOrderDto = iterator.next();
            if(checkOrderDto.getOrderId() == orderId){
                ordersDelivered.add(checkOrderDto);
                ordersOnProcess.remove(checkOrderDto);
                break;
            }
        }
    }

    private void updateItemOrder(int orderId)
    {
        ItemOrder itemOrder = itemOrderService.getItemOrderById(orderId);
        itemOrder.setDeliveryStatus(DeliveryStatus.DELIVERED);
        itemOrderService.updateItemOrder(itemOrder);
    }

}
