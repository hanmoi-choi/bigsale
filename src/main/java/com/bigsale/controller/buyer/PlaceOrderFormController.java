package com.bigsale.controller.buyer;

import com.bigsale.controller.dto.PlaceOrderDto;
import com.bigsale.orm.model.DeliveryStatus;
import com.bigsale.orm.model.Item;
import com.bigsale.orm.model.ItemOrder;
import com.bigsale.orm.model.User;
import com.bigsale.service.ItemOrderService;
import com.bigsale.service.ItemService;
import com.bigsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/buyer/placeOrderForm")
@SessionAttributes({"placeOrderDto"})
public class PlaceOrderFormController {
    public static final String FORM_PAGE_ONE = "/buyer/placeOrderFormPageOne";
    public static final String FORM_PAGE_CONFIRM = "/buyer/placeOrderFormPageConfirm";
    private static final String REDIRECT_TO_BUYER_INDEX = "redirect:/buyer/welcome.html";
    private Map<Integer, String> pageForms;
    private Validator validator;

    static final Logger logger = LoggerFactory.getLogger(PlaceOrderFormController.class);

    @Autowired
    PlaceOrderDto placeOrderDto;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    ItemOrderService itemOrderService;

    private Item itemById;


    @Autowired
    public PlaceOrderFormController(Validator validator)
    {
        this.validator = validator;
        initAddSellerFromMap();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
                            HttpServletResponse response,
                            Model model)
    {
        Integer itemId = (Integer) request.getSession().getAttribute("itemId");
        itemById = itemService.getItemById(itemId);

        placeOrderDto.fiilWith(itemById);
        model.addAttribute("placeOrderDto", placeOrderDto);

        return FORM_PAGE_ONE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("placeOrderDto") PlaceOrderDto placeOrderDto,
            BindingResult placeOrderResult,
            SessionStatus status,
            @RequestParam("_page") int currentPage
    )
    {
        if (userClickedCancel(request))
        {
            return REDIRECT_TO_BUYER_INDEX;
        }
        else if (userIsFinished(request))
        {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserById(userId);

            changeItemInfo(itemById, placeOrderDto);
            placeOrder(placeOrderDto, user);

            status.setComplete();
            return REDIRECT_TO_BUYER_INDEX;
        }
        else
        {
            int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
            // If targetPage is lesser than current page, buyer clicked 'Previous'
            if (targetPage < currentPage)
            {
                return pageForms.get(targetPage);
            }
            else
            {
                placeOrderDto.checkStockAmount();
                placeOrderDto.calculateTotalPrice();
                validator.validate(placeOrderDto, placeOrderResult);
            }

            if (!placeOrderResult.hasErrors())
            {
                //No errors, return target page
                return (String) pageForms.get(targetPage);
            }
            else
            {
                // Errors, return current page
                return (String) pageForms.get(currentPage);
            }
        }
    }

    private void placeOrder(PlaceOrderDto placeOrderDto, User user)
    {
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setItem(itemById);
        itemOrder.setOrderQuantity(placeOrderDto.getOrderAmount());
        itemOrder.setDeliveryStatus(DeliveryStatus.PREPARING);
        itemOrder.setUser(user);
        itemOrder.setItemOrderDate(new Date());

        user.getItemOrders().add(itemOrder);

        itemOrderService.addItemOrder(itemOrder);

    }

    private void changeItemInfo(Item itemById, PlaceOrderDto placeOrderDto)
    {
        itemById.deduceStock(placeOrderDto.getOrderAmount());
        itemService.updateItem(itemById);
    }

    private void initAddSellerFromMap()
    {
        pageForms = new HashMap<Integer, String>();
        pageForms.put(0, FORM_PAGE_ONE);
        pageForms.put(1, FORM_PAGE_CONFIRM);
    }

    private boolean userIsFinished(HttpServletRequest request)
    {
        return request.getParameter("_finish") != null;
    }

    private boolean userClickedCancel(HttpServletRequest request)
    {
        return request.getParameter("_cancel") != null;
    }
}
