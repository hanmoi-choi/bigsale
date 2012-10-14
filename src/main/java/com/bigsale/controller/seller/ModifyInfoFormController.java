package com.bigsale.controller.seller;

import com.bigsale.controller.dto.SellerModifyDto;
import com.bigsale.orm.model.Seller;
import com.bigsale.service.SellerService;
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
@RequestMapping("/admin/modifySellerForm")
@SessionAttributes({"sellerModifyDto"})
public class ModifyInfoFormController {
    private static final String FORM_PAGE_ONE = "/admin/modifySellerFormPageOne";
    private static final String FORM_PAGE_CONFIRM = "/admin/modifySellerFormPageConfirm";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/admin/welcome.html";

    private Map<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(ModifyInfoFormController.class);

    @Autowired
    SellerService sellerService;

    @Autowired
    SellerModifyDto sellerModifyDto;

    @Autowired
    public ModifyInfoFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
                            HttpServletResponse response,
                            Model model)
    {
        String userId = (String) request.getSession().getAttribute("userId");

        getSellerInfo(sellerModifyDto, userId);
        model.addAttribute("sellerModifyDto", sellerModifyDto);

        initAddSellerFromMap();

        return FORM_PAGE_ONE;
    }

    private void getSellerInfo(SellerModifyDto sellerModifyDto, String userId) {
        Seller seller = sellerService.getSellerById(userId);

        sellerModifyDto.setSellerId(seller.getSellerId());
        sellerModifyDto.setFullName(seller.getFullName());
        sellerModifyDto.setEmail(seller.getEmail());
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
            @ModelAttribute("sellerModifyDto") SellerModifyDto sellerModifyDto,
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
            modifySeller(sellerModifyDto);

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
                validator.validate(sellerModifyDto, sellerResult);
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

    private void setDefaultValue(Seller seller)
    {
        seller.setDateCreated(new Date());
        seller.setSellerLevel(BRONZE);
        seller.setLoginCount(1);
    }

    private void modifySeller(SellerModifyDto sellerModifyDto)
    {
        Seller seller = new Seller();

        setDefaultValue(seller);
        setSellerInfo(seller, sellerModifyDto);
        sellerService.updateSeller(seller);
    }



    private void setSellerInfo(Seller seller, SellerModifyDto sellerSignUpDto)
    {
        seller.setSellerId(sellerSignUpDto.getSellerId());

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
