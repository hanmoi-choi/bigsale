package com.bigsale.controller.user;

import com.bigsale.orm.model.Address;
import com.bigsale.orm.model.User;
import com.bigsale.service.AddressService;
import com.bigsale.service.UserService;
import com.bigsale.view.validator.AddUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/addSellerForm")
@SessionAttributes({"user", "address"})
public class AdminAddUserFormController {
    private static final String FORM_PAGE_ONE = "/admin/addSellerFormPageOne";
    private static final String FORM_PAGE_TWO = "/admin/addSellerFormPageTwo";
    private static final String FORM_PAGE_CONFIRM = "/admin/addSellerFormPageConfirm";
    private static final String SUCCESS_PAGE = "registrationSuccess";
    private static final String REDIRECT_TO_SUCCESS_PAGE = "redirect:/admin/registrationSuccess";
    private static final String REDIRECT_TO_HOMEPAGE = "redirect:/";

    private AddUserValidator addUserValidator;

    static final Logger logger = LoggerFactory.getLogger(AdminAddUserFormController.class);

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    public AdminAddUserFormController(AddUserValidator addUserValidator) {
        this.addUserValidator = addUserValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        User user = new User();
        Address address = new Address();
        address.getUsers().add(user);
        user.setAddress(address);

        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return FORM_PAGE_ONE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("user") User user,
            BindingResult result, SessionStatus status,
            @RequestParam("_page") int currentPage, Model model) {



        Map<Integer, String> pageForms = new HashMap<Integer, String>();
        pageForms.put(0, FORM_PAGE_ONE);
        pageForms.put(1, FORM_PAGE_TWO);
        pageForms.put(2, FORM_PAGE_CONFIRM);


        if (userClickedCancel(request)) {
            return REDIRECT_TO_HOMEPAGE;
        } else if (userIsFinished(request)) {
            //new UserValidator().validate(user, result);
//            addUserValidator.validate(user, result);
            if (!result.hasErrors()) {
                persistUser(user);
                status.setComplete();
                return SUCCESS_PAGE;
            } else {
                // Errors
                return pageForms.get(currentPage);
            }
        } else {
            int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
            logger.warn("currentPage : {}", currentPage);
            logger.warn("targetPage : {}", targetPage);
            // If targetPage is lesser than current page, user clicked 'Previous'
            if (targetPage < currentPage) {
                return pageForms.get(targetPage);
            }
            // User clicked next
            // Validate data based on page
//            switch (currentPage) {
//                case 0:
//                    //new UserValidator().validatePage1Form(user, result);
//                    addUserValidator.validatePage1Form(user, result);
//                    break;
//                case 1:
//                    //new UserValidator().validatePage2Form(user, result);
//                    addUserValidator.validatePage2Form(user, result);
//                    break;
//            }
//            if (!result.hasErrors()) {
            // No errors, return target page
            return (String) pageForms.get(targetPage);
//            } else {
//                // Errors, return current page
//                return (String) pageForms.get(currentPage);
//            }
//        }
        }

    }

    private void persistUser(User user) {
        userService.addUser(user);
    }

    private boolean userIsFinished(HttpServletRequest request) {
        return request.getParameter("_finish") != null;
    }

    private boolean userClickedCancel(HttpServletRequest request) {
        return request.getParameter("_cancel") != null;
    }

}
