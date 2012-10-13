package com.bigsale.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 8/10/12
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class CommonController {

    static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("welcome")
    public String index()
    {
        logger.warn("index");
        return "/index";
    }

    @RequestMapping("loggedin")
    public String loggedin()
    {
        logger.warn("index");
        return "/home";
    }

    @RequestMapping("login")
    public String logIn(@RequestParam("id") String id,
                        @RequestParam("password") String password)
    {
        logger.warn("login id:{} pw:{}", id, password);
        return "";
    }

    @RequestMapping("signUp")
    public String signUp()
    {
        return "redirect:/signUpForm.html";
    }

    @RequestMapping("searchItem")
    public ModelAndView searchItem(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("_itemName") String itemName,
            SessionStatus status)
    {
        logger.warn("searchItem: {}", itemName);
        return null;
    }
}
