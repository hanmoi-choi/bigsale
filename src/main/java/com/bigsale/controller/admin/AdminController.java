package com.bigsale.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("admin")
public class AdminController {

    static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/welcome")
    public String adminIndex()
    {
        return "/admin/index";
    }

    @RequestMapping("addSeller")
    public String addSeller()
    {
        return "redirect:/admin/addSellerForm.html";
    }


    @RequestMapping("/querySeller")
    public String querySeller()
    {
        return "redirect:/admin/querySellerForm.html";
    }

    @RequestMapping(value = "/modifySeller", method = RequestMethod.POST)
    public String modifySeller( HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam("id") String userId)
    {
        logger.debug("ID: {}", userId);
        request.getSession().setAttribute("userId", userId);
        return "redirect:/admin/modifySellerForm.html";
    }

    @RequestMapping("/makeReport")
    public String addSellers()
    {
        return "redirect:/admin/makeReportForm.html";
    }


    @RequestMapping("/logout")
    public String logout()
    {
        return "redirect:/j_spring_security_logout";
    }
}
