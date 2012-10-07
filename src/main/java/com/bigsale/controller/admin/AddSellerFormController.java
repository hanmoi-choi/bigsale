package com.bigsale.controller.admin;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.bigsale.orm.model.User.UserLevel.BRONZE;
import static com.bigsale.orm.model.User.UserType.SELLER;

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
public class AddSellerFormController {
    private static final String FORM_PAGE_ONE = "/admin/addSellerFormPageOne";
    private static final String FORM_PAGE_TWO = "/admin/addSellerFormPageTwo";
    private static final String FORM_PAGE_CONFIRM = "/admin/addSellerFormPageConfirm";
    private static final String SUCCESS_PAGE = "/admin/registrationSuccess";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/bigsale/admin";

    private Map<Integer, String> pageForms;
    private AddUserValidator addUserValidator;
    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    public AddSellerFormController(AddUserValidator addUserValidator)
    {
        this.addUserValidator = addUserValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        User user = new User();
        Address address = new Address();

        model.addAttribute("user", user);
        model.addAttribute("address", address);

        initAddSellerFromMap();

        return FORM_PAGE_ONE;
    }

    private void initAddSellerFromMap()
    {
        pageForms = new HashMap<Integer, String>();
        pageForms.put(0, FORM_PAGE_ONE);
        pageForms.put(1, FORM_PAGE_TWO);
        pageForms.put(2, FORM_PAGE_CONFIRM);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("user") User user,
            @ModelAttribute("address") Address address,
            BindingResult result, SessionStatus status,
            @RequestParam("_page") int currentPage, Model model)
    {

        if (userClickedCancel(request))
        {
            return REDIRECT_TO_ADMIN_INDEX;
        }
        else if (userIsFinished(request))
        {
            //new UserValidator().validate(buyer, result);
//            addUserValidator.validate(buyer, result);
            if (!result.hasErrors())
            {
                setDefaultValue(user);
                persistUser(user, address);
                status.setComplete();
                return SUCCESS_PAGE;
            }
            else
            {
                // Errors
                return pageForms.get(currentPage);
            }
        }
        else
        {
            int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
            logger.warn("currentPage : {}", currentPage);
            logger.warn("targetPage : {}", targetPage);
            // If targetPage is lesser than current page, buyer clicked 'Previous'
            if (targetPage < currentPage)
            {
                return pageForms.get(targetPage);
            }
            // User clicked next
            // Validate data based on page
//            switch (currentPage) {
//                case 0:
//                    //new UserValidator().validatePage1Form(buyer, result);
//                    addUserValidator.validatePage1Form(buyer, result);
//                    break;
//                case 1:
//                    //new UserValidator().validatePage2Form(buyer, result);
//                    addUserValidator.validatePage2Form(buyer, result);
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

    private void setDefaultValue(User user)
    {
        user.setUserType(SELLER);
        user.setDateCreated(new Date());
        user.setUserLevel(BRONZE);
    }

    private void persistUser(User user, Address address)
    {
        user.setAddress(address);
        address.getUsers().add(user);
        addressService.addAddress(address);
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
