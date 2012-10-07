package com.bigsale.controller.buyer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @RequestMapping("/checkOrder")
    public String addItems()
    {
        return "redirect:/buyer/checkOrderForm.html";
    }


    @RequestMapping("/logout")
    public String logout()
    {
        return "redirect:/index.html";
    }
}
