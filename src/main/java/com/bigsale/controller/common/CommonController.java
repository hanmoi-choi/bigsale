package com.bigsale.controller.common;

import com.bigsale.controller.dto.ItemSearchDto;
import com.bigsale.orm.model.Item;
import com.bigsale.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 8/10/12
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class CommonController {

    static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    public static final String VIEW_NAME = "/itemSearchResultForm";

    @Autowired
    ItemService itemService;

    @RequestMapping("welcome")
    public String index()
    {
        logger.warn("index");
        return "/index";
    }

    @RequestMapping("loggedin")
    public String loggedin()
    {
        logger.warn("index");
        return "/home";
    }

    @RequestMapping("login")
    public String logIn(@RequestParam("id") String id,
                        @RequestParam("password") String password)
    {
        logger.warn("login id:{} pw:{}", id, password);
        return "";
    }

    @RequestMapping("signUp")
    public String signUp()
    {
        return "redirect:/signUpForm.html";
    }

    @RequestMapping("searchItem")
    public String searchItem(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("_itemName") String itemName,
            SessionStatus status)
    {
        logger.warn("searchItem: {}", itemName);
        ItemSearchDto itemSearchDto = new  ItemSearchDto();
        itemSearchDto.setItemName(itemName);

        List<Item> itemList = itemService.findItemBySearchCriteria(itemSearchDto);

        request.setAttribute("itemList", itemList);
        return VIEW_NAME;
    }
}
