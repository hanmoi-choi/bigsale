package com.bigsale.controller.common;

import com.bigsale.controller.dto.UserSignUpDto;
import com.bigsale.orm.model.Address;
import com.bigsale.orm.model.User;
import com.bigsale.service.AddressService;
import com.bigsale.service.UserService;
import com.bigsale.util.CipherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import static com.bigsale.orm.model.User.UserType.BUYER;

@Controller
@RequestMapping("/signUpForm")
@SessionAttributes({"userSignUpDto"})
public class SignUpFormController {
    private static final String FORM_PAGE_ONE = "/signUpFormPageOne";
    private static final String FORM_PAGE_CONFIRM = "/signUormPageConfirm";
    private static final String SUCCESS_PAGE = "/admin/registrationSuccess";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/bigsale/admin/";

    private Map<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(SignUpFormController.class);

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserSignUpDto userSignUpDto;

    @Autowired
    public SignUpFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        model.addAttribute("userSignUpDto", userSignUpDto);

        initAddSellerFromMap();

        return FORM_PAGE_ONE;
    }

    private void initAddSellerFromMap()
    {
        pageForms = new HashMap<Integer, String>();
        pageForms.put(0, FORM_PAGE_ONE);
        pageForms.put(1, FORM_PAGE_CONFIRM);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("userSignUpDto") UserSignUpDto userSignUpDto,
            BindingResult userResult,
            SessionStatus status,
            @RequestParam("_page") int currentPage, Model model)
    {
        logger.warn("{}", userSignUpDto);

        if (userClickedCancel(request))
        {
            return REDIRECT_TO_ADMIN_INDEX;
        }
        else if (userIsFinished(request))
        {
            persistUser(userSignUpDto);
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
            else
            {
                validateInput(userSignUpDto, userResult);
            }

            if (!userResult.hasErrors())
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

    private void validateInput(UserSignUpDto userSignUpDto, BindingResult userResult)
    {// User clicked next Validate data based on page
        userSignUpDto.isPasswordInputMatched();
        userSignUpDto.isIdDuplicated();
        validator.validate(userSignUpDto, userResult);
    }

    private void setDefaultValue(User user)
    {
        user.setUserType(BUYER);
        user.setDateCreated(new Date());
        user.setUserLevel(BRONZE);
    }

    private void persistUser(UserSignUpDto userSignUpDto)
    {
        User user = new User();
        Address address = new Address();

        setDefaultValue(user);
        setUserInfo(user, userSignUpDto);
        setAddressInfo(address, userSignUpDto);
        user.setAddress(address);
        address.getUsers().add(user);
        addressService.addAddress(address);
    }

    private void setAddressInfo(Address address, UserSignUpDto userSignUpDto)
    {
        address.setStreet(userSignUpDto.getStreet());
        address.setCity(userSignUpDto.getCity());
        address.setState(userSignUpDto.getState());
        address.setZipcode(userSignUpDto.getZipcode());
    }

    private void setUserInfo(User user, UserSignUpDto userSignUpDto)
    {
        user.setUserId(userSignUpDto.getUserId());
        String password = CipherUtil.hashPassword(userSignUpDto.getPassword());
        user.setPassword(password);
        user.setFullName(userSignUpDto.getFullName());
        user.setEmail(userSignUpDto.getEmail());
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
