package com.bigsale.controller.buyer;

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
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/buyer/placeOrderForm")
public class PlaceOrderFormController {
    public static final String FORM_PAGE_FOR_QUERY_INPUT = "/buyer/placeOrderFormPageOne";
    public static final String FORM_PAGE_FOR_QUERY_RESULT = "/buyer/placeOrderFormPageResult";

    static final Logger logger = LoggerFactory.getLogger(PlaceOrderFormController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        return FORM_PAGE_FOR_QUERY_INPUT;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            BindingResult result, SessionStatus status,
            Model model)
    {
        return FORM_PAGE_FOR_QUERY_RESULT;
    }
}
