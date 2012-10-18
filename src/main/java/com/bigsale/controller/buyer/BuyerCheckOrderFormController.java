package com.bigsale.controller.buyer;

import com.bigsale.controller.dto.CheckOrderDto;
import com.bigsale.orm.model.DeliveryStatus;
import com.bigsale.orm.model.ItemOrder;
import com.bigsale.orm.model.User;
import com.bigsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 10:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/buyer/checkOrderForm")
public class BuyerCheckOrderFormController {
    public static final String CHECK_ORDER_FORM = "/buyer/checkOrderResultForm";
    static final Logger logger = LoggerFactory.getLogger(BuyerCheckOrderFormController.class);

    private Set<CheckOrderDto> ordersOnProcess = new HashSet<CheckOrderDto>();
    private Set<CheckOrderDto> ordersDelivered = new HashSet<CheckOrderDto>();

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request, HttpServletResponse response,
                            SessionStatus status,
                            Model model)
    {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserById(userId);
        Set<ItemOrder> itemOrders = user.getItemOrders();

        ordersDelivered.clear();
        ordersOnProcess.clear();

        Iterator<ItemOrder> iterator = itemOrders.iterator();

        while(iterator.hasNext()){
            ItemOrder itemOrder = iterator.next();

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

        model.addAttribute("ordersOnProcess", ordersOnProcess);
        model.addAttribute("ordersDelivered", ordersDelivered);
        return CHECK_ORDER_FORM;
    }

}
