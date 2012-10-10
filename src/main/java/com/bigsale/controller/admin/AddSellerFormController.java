package com.bigsale.controller.admin;

import com.bigsale.controller.dto.AddressDto;
import com.bigsale.controller.dto.UserDto;
import com.bigsale.orm.model.Address;
import com.bigsale.orm.model.User;
import com.bigsale.service.AddressService;
import com.bigsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
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
@SessionAttributes({"userDto", "addressDto"})
public class AddSellerFormController {
    private static final String FORM_PAGE_ONE = "/admin/addSellerFormPageOne";
    private static final String FORM_PAGE_TWO = "/admin/addSellerFormPageTwo";
    private static final String FORM_PAGE_CONFIRM = "/admin/addSellerFormPageConfirm";
    private static final String SUCCESS_PAGE = "/admin/registrationSuccess";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/bigsale/admin";

    private Map<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    public AddSellerFormController(Validator validator)
    {
        this.validator = validator;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        UserDto userDto = new UserDto();
        AddressDto addressDto = new AddressDto();


        model.addAttribute("userDto", userDto);
        model.addAttribute("addressDto", addressDto);


        initAddSellerFromMap();

        return FORM_PAGE_TWO;
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
            @ModelAttribute("userDto") UserDto userDto,
            BindingResult userResult,
            @ModelAttribute("addressDto") AddressDto addressDto,
            BindingResult addressResult,
            SessionStatus status,
            @RequestParam("_page") int currentPage, Model model)
    {
        Errors errors = null;

        if (userClickedCancel(request))
        {
            return REDIRECT_TO_ADMIN_INDEX;
        }
        else if (userIsFinished(request))
        {
//            setDefaultValue(user);
//            persistUser(user, address);
            status.setComplete();
            return SUCCESS_PAGE;

        }
        else
        {
            int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
            // If targetPage is lesser than current page, buyer clicked 'Previous'
            if (targetPage < currentPage)
            {
                return pageForms.get(targetPage);
            }
            // User clicked next
//            Validate data based on page
            switch (currentPage)
            {
                case 0:
                    userDto.isPasswordInputMatched();
                    validator.validate(userDto, userResult);
                    break;

                case 1:
                    validator.validate(addressDto, addressResult);
                    break;
            }
            if (!userResult.hasErrors() && !addressResult.hasErrors())
            {
                //No errors, return target page
                return (String) pageForms.get(targetPage);
            }
            else
            {
                // Errors, return current page
                return (String) pageForms.get(currentPage);
            }

        }

    }

//    protected Map referenceData(HttpServletRequest request) throws Exception {
//
//        Map referenceData = new HashMap();
//        Map<String, String> stateMap;
//        stateMap = new HashMap<String, String>();
//        stateMap.put("VIC", "VIC");
//        stateMap.put("WA", "WA");
//
//        referenceData.put("stateList", stateMap);
//
//        return referenceData;
//    }

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
