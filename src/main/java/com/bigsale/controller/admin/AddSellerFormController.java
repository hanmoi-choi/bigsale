package com.bigsale.controller.admin;

import com.bigsale.controller.dto.SellerSignUpDto;
import com.bigsale.orm.model.Seller;
import com.bigsale.service.SellerService;
import com.bigsale.util.CipherUtil;
import com.bigsale.util.MailService;
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

import static com.bigsale.orm.model.Level.BRONZE;

@Controller
@RequestMapping("/admin/addSellerForm")
@SessionAttributes({"sellerSignUpDto"})

public class AddSellerFormController {
    private static final String FORM_PAGE_ONE = "/admin/addSellerFormPageOne";
    private static final String FORM_PAGE_CONFIRM = "/admin/addSellerFormPageConfirm";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/admin/welcome.html";

    private Map<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);

    @Autowired
    SellerService sellerService;

    @Autowired
    SellerSignUpDto sellerSignUpDto;

    @Autowired
    private MailService mailService;

    @Autowired
    public AddSellerFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        model.addAttribute("sellerSignUpDto", sellerSignUpDto);

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
            @ModelAttribute("sellerSignUpDto") SellerSignUpDto sellerSignUpDto,
            BindingResult sellerResult,
            SessionStatus status,
            @RequestParam("_page") int currentPage, Model model)
    {

        if (sellerClickedCancel(request))
        {
            return REDIRECT_TO_ADMIN_INDEX;
        }
        else if (sellerIsFinished(request))
        {
            persistSeller(sellerSignUpDto);
            sendMailToUser(sellerSignUpDto);
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
                validateInput(sellerSignUpDto, sellerResult);
            }

            if (!sellerResult.hasErrors())
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

    private void sendMailToUser(SellerSignUpDto sellerSignUpDto) {
        mailService.sendSignUpSuccessMail(sellerSignUpDto.getEmail(),
                sellerSignUpDto.getSellerId(),
                sellerSignUpDto.getPassword());
    }

    private void validateInput(SellerSignUpDto sellerSignUpDto, BindingResult sellerResult)
    {// Seller clicked next Validate data based on page
        sellerSignUpDto.isPasswordInputMatched();
        sellerSignUpDto.isIdDuplicated();
        sellerSignUpDto.isEmailDuplicated();
        validator.validate(sellerSignUpDto, sellerResult);
    }

    private void setDefaultValue(Seller seller)
    {
        seller.setDateCreated(new Date());
        seller.setSellerLevel(BRONZE);
        seller.setLoginCount(1);
    }

    private void persistSeller(SellerSignUpDto sellerSignUpDto)
    {
        Seller seller = new Seller();

        setDefaultValue(seller);
        setSellerInfo(seller, sellerSignUpDto);
        sellerService.addSeller(seller);
    }



    private void setSellerInfo(Seller seller, SellerSignUpDto sellerSignUpDto)
    {
        seller.setSellerId(sellerSignUpDto.getSellerId());
        String password = CipherUtil.hashPassword(sellerSignUpDto.getPassword());
        seller.setPassword(password);
        seller.setFullName(sellerSignUpDto.getFullName());
        seller.setEmail(sellerSignUpDto.getEmail());
    }

    private boolean sellerIsFinished(HttpServletRequest request)
    {
        return request.getParameter("_finish") != null;
    }

    private boolean sellerClickedCancel(HttpServletRequest request)
    {
        return request.getParameter("_cancel") != null;
    }

}
