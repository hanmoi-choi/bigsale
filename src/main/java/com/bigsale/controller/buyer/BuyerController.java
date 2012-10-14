package com.bigsale.controller.buyer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 3:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes
@RequestMapping("buyer")
public class BuyerController {

    static final Logger logger = LoggerFactory.getLogger(BuyerController.class);

    @RequestMapping("/welcome")
    public String buyerIndex()
    {
        return "/buyer/index";
    }

    @RequestMapping("/modifyInfo")
    public String modifyInfo()
    {
        return "redirect:/buyer/modifyInfoForm.html";
    }

    @RequestMapping("/checkOrder")
    public String addItems()
    {
        return "redirect:/buyer/checkOrderForm.html";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String makeOrder( HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("itemId") String itemId)
    {
        request.getSession().setAttribute("itemId", itemId);
        return "redirect:/buyer/placeOrderForm.html";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        return "redirect:/j_spring_security_logout";
    }
}

