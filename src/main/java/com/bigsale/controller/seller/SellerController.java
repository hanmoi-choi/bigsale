package com.bigsale.controller.seller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 6/10/12
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("seller")
public class SellerController {

    static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @RequestMapping("/welcome")
    public String sellerIndex()
    {
        return "/seller/index";
    }

    @RequestMapping("/addItem")
    public String addItem()
    {
        return "redirect:/seller/addItemForm.html";
    }


    @RequestMapping("/queryItem")
    public String queryItem()
    {
        return "redirect:/seller/queryItemForm.html";
    }


    @RequestMapping("/checkOrder")
    public String addItems()
    {
        return "redirect:/seller/checkOrderForm.html";
    }


    @RequestMapping("/logout")
    public String logout()
    {
        return "redirect:/index.html";
    }
}

