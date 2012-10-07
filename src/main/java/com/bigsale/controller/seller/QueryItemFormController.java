package com.bigsale.controller.seller;

import com.bigsale.controller.admin.AddSellerFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/seller/queryItemForm")
public class QueryItemFormController {

    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            BindingResult result, SessionStatus status,
            Model model)
    {
        return null;
    }
}
