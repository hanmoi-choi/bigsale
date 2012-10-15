package com.bigsale.controller.seller;

import com.bigsale.controller.dto.ItemAddDto;
import com.bigsale.orm.model.Item;
import com.bigsale.service.ItemService;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/seller/modifyItemForm")
@SessionAttributes({"itemModifyDto"})
public class ModifyItemFormController {
    public static final String FORM_PAGE_ONE = "/seller/modifyItemFormPageOne";
    public static final String FORM_PAGE_CONFIRM = "/seller/modifyItemFormPageConfirm";
    private static final String REDIRECT_TO_SELLER_INDEX = "redirect:/seller/welcome.html";

    private HashMap<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(ModifyItemFormController.class);

    @Autowired
    ItemAddDto itemModifyDto;

    @Autowired
    ItemService itemService;

    @Autowired
    SellerService sellerService;
    private Item itemById;

    @Autowired
    public ModifyItemFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(HttpServletRequest request,
                            HttpServletResponse response,
                            Model model)
    {
        initAddSellerFromMap();

        Integer itemId = (Integer) request.getSession().getAttribute("itemId");
        itemById = itemService.getItemById(itemId);

        itemModifyDto.fiilWith(itemById);

        model.addAttribute("itemModifyDto", itemModifyDto);
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
            @ModelAttribute("itemModifyDto")ItemAddDto itemModifyDto,
            BindingResult itemAddresult, SessionStatus status,
            @RequestParam("_page") int currentPage,
            HttpSession session)
    {

        if (sellerClickedCancel(request))
        {
            return REDIRECT_TO_SELLER_INDEX;
        }
        else if (sellerIsFinished(request))
        {
            updateItem(itemModifyDto, itemById);
            status.setComplete();
            return REDIRECT_TO_SELLER_INDEX;
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
                validator.validate(itemModifyDto, itemAddresult);
            }

            if (!itemAddresult.hasErrors())
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

    private void updateItem(ItemAddDto itemAddDto, Item item) {

        item.setItemName(itemAddDto.getItemName());
        item.setStockQuantity(itemAddDto.getStockQuantity());
        item.setDescription(itemAddDto.getDescription());
        item.setPrice(itemAddDto.getPrice());
        item.setDiscountRate(itemAddDto.getDiscountRate());

        itemService.updateItem(item);
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
