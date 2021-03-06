package com.bigsale.controller.seller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/modifyInfo")
    public String modifyInfo()
    {
        return "redirect:/seller/modifyInfoForm.html";
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

    @RequestMapping(value = "/modifyItemInfo", method = RequestMethod.POST)
    public String modifyItemInfo(@RequestParam("itemId")Integer itemId,
                                 HttpServletRequest request)
    {
        request.getSession().setAttribute("itemId", itemId);
        logger.debug("RequestParam: {}", itemId);
        return "redirect:/seller/modifyItemForm.html";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        return "redirect:/j_spring_security_logout";
    }
}

