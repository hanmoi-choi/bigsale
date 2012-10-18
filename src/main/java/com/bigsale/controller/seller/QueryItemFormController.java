package com.bigsale.controller.seller;

import com.bigsale.controller.admin.AddSellerFormController;
import com.bigsale.controller.dto.ItemSearchDto;
import com.bigsale.controller.dto.ItemSearchResultDto;
import com.bigsale.orm.model.Item;
import com.bigsale.orm.model.Seller;
import com.bigsale.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/seller/queryItemForm")
public class QueryItemFormController {
    public static final String FORM_PAGE_FOR_QUERY_INPUT = "/seller/queryItemFormPageOne";
    public static final String FORM_PAGE_FOR_QUERY_RESULT = "/seller/queryItemFormPageResult";

    static final Logger logger = LoggerFactory.getLogger(AddSellerFormController.class);
    @Autowired
    private ItemSearchDto itemSearchDto;

    @Autowired
    SellerService sellerService;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        model.addAttribute("itemSearchDto", itemSearchDto);

        return FORM_PAGE_FOR_QUERY_INPUT;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("itemSearchDto")ItemSearchDto itemSearchDto,
            BindingResult result,  SessionStatus status,
            Model model)
    {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerService.getSellerById(sellerId);

        Set<Item> itemList = seller.getItems();

        logger.debug("Item List Size: {}", itemList.size());
        List<ItemSearchResultDto> itemSearchResultList = new ArrayList<ItemSearchResultDto>();

        Iterator<Item> itemIterator = itemList.iterator();

        while (itemIterator.hasNext()){
            Item item = itemIterator.next();
            if(doesItemContainSearchPattern(itemSearchDto, item)
               || isSearchingForAllItems(itemSearchDto)){
                ItemSearchResultDto dto = new ItemSearchResultDto();
                dto.fillWith(item);
                itemSearchResultList.add(dto);
            }
        }

        request.setAttribute("itemSearchResultList", itemSearchResultList);

        return FORM_PAGE_FOR_QUERY_RESULT;
    }

    private boolean isSearchingForAllItems(ItemSearchDto itemSearchDto) {
        return itemSearchDto.getItemName().isEmpty();
    }

    private boolean doesItemContainSearchPattern(ItemSearchDto itemSearchDto, Item item)
    {
        return item.getItemName().contains(itemSearchDto.getItemName());
    }
}
