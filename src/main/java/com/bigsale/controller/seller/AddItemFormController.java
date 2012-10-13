package com.bigsale.controller.seller;

import com.bigsale.controller.admin.AddSellerFormController;
import com.bigsale.controller.dto.ItemAddDto;
import com.bigsale.orm.model.Item;
import com.bigsale.orm.model.Seller;
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
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/seller/addItemForm")
@SessionAttributes({"itemAddDto"})
public class AddItemFormController {
    public static final String FORM_PAGE_ONE = "/seller/addItemFormPageOne";
    public static final String FORM_PAGE_CONFIRM = "/seller/addItemFormPageConfirm";
    private static final String REDIRECT_TO_SELLER_INDEX = "redirect:/seller/welcome.html";

    private HashMap<Integer, String> pageForms;
    private Validator validator;
    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);

    @Autowired
    ItemAddDto itemAddDto;

    @Autowired
    ItemService itemService;

    @Autowired
    SellerService sellerService;

    @Autowired
    public AddItemFormController(Validator validator)
    {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        initAddSellerFromMap();

        itemAddDto.setDiscountRate(0);
        itemAddDto.setPrice(0);
        itemAddDto.setStockQuantity(0);

        model.addAttribute("itemAddDto", itemAddDto);
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
            @ModelAttribute("itemAddDto")ItemAddDto itemAddDto,
            BindingResult itemAddresult, SessionStatus status,
            @RequestParam("_page") int currentPage,
            Model model)
    {

        if (sellerClickedCancel(request))
        {
            return REDIRECT_TO_SELLER_INDEX;
        }
        else if (sellerIsFinished(request))
        {
            String sellerId = (String) request.getSession().getAttribute("userId");
            persistItem(itemAddDto, sellerId);
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
                validator.validate(itemAddDto, itemAddresult);
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

    private void persistItem(ItemAddDto itemAddDto, String sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);

        Item item = new Item();
        item.setItemName(itemAddDto.getItemName());
        item.setStockQuantity(itemAddDto.getStockQuantity());
        item.setDescription(itemAddDto.getDescription());
        item.setPrice(itemAddDto.getPrice());
        item.setDiscountRate(itemAddDto.getDiscountRate());
        item.getSellers().add(seller);

        seller.getItems().add(item);

        itemService.addItem(item);
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
