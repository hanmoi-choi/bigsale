package com.bigsale.controller.buyer;

import com.bigsale.controller.dto.UserModifyDto;
import com.bigsale.orm.model.User;
import com.bigsale.service.UserService;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/buyer/modifyInfoForm")
@SessionAttributes({"userModifyDto"})
public class ModifyBuyerInfoFormController {
    private static final String FORM_PAGE_ONE = "/buyer/modifyInfoFormPageOne";
    private static final String FORM_PAGE_CONFIRM = "/buyer/modifyInfoFormPageConfirm";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/buyer/welcome.html";

    private Map<Integer, String> pageForms;
    private User userModified;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(ModifyBuyerInfoFormController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserModifyDto userModifyDto;

    @Autowired
    public ModifyBuyerInfoFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            Model model)
    {
        String userId = (String) session.getAttribute("userId");
        logger.debug("session: {}",session.getAttributeNames());
        logger.debug("userId: {}", userId);
        logger.debug("userId: {}", request.getSession().getAttribute("userId"));


        getUserInfo(userModifyDto, userId);
        model.addAttribute("userModifyDto", userModifyDto);

        initAddUserFromMap();

        return FORM_PAGE_ONE;
    }

    private void getUserInfo(UserModifyDto userModifyDto, String userId) {
        userModified = userService.getUserById(userId);

        userModifyDto.setUserId(userModified.getUserId());
        userModifyDto.setFullName(userModified.getFullName());
        userModifyDto.setEmail(userModified.getEmail());
        userModifyDto.setStreet(userModified.getAddress().getStreet());
        userModifyDto.setCity(userModified.getAddress().getCity());
        userModifyDto.setState(userModified.getAddress().getState());
        userModifyDto.setZipcode(userModified.getAddress().getZipcode());
    }

    private void initAddUserFromMap()
    {
        pageForms = new HashMap<Integer, String>();
        pageForms.put(0, FORM_PAGE_ONE);
        pageForms.put(1, FORM_PAGE_CONFIRM);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("userModifyDto") UserModifyDto userModifyDto,
            BindingResult userResult,
            SessionStatus status,
            @RequestParam("_page") int currentPage, Model model)
    {

        if (userClickedCancel(request))
        {
            return REDIRECT_TO_ADMIN_INDEX;
        }
        else if (userIsFinished(request))
        {
            modifyUser(userModifyDto);

            status.setComplete();
            return REDIRECT_TO_ADMIN_INDEX;
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
                validator.validate(userModifyDto, userResult);
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

    private void modifyUser(UserModifyDto userModifyDto)
    {
        userModified.setFullName(userModifyDto.getFullName());
        userModified.setEmail(userModifyDto.getEmail());
        userModified.getAddress().setStreet(userModifyDto.getStreet());
        userModified.getAddress().setCity(userModifyDto.getCity());
        userModified.getAddress().setState(userModifyDto.getState());
        userModified.getAddress().setZipcode(userModifyDto.getZipcode());

        userService.updateUser(userModified);
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
