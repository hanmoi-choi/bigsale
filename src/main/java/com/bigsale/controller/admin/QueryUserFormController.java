package com.bigsale.controller.admin;

import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.controller.dto.UserSearchResultDto;
import com.bigsale.orm.model.Seller;
import com.bigsale.orm.model.User;
import com.bigsale.service.SellerService;
import com.bigsale.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/querySellerForm")
public class QueryUserFormController {
    @Autowired
    SellerService sellerService;

    @Autowired
    UserService userService;

    @Autowired
    UserSearchDto userSearchDto;

    static final Logger logger = LoggerFactory.getLogger(QueryUserFormController.class);
    public static final String FORM_PAGE_FOR_QUERY_INPUT = "/admin/queryUserFormPageOne";
    public static final String FORM_PAGE_FOR_QUERY_RESULT = "/admin/queryUserFormPageResult";

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model)
    {
        model.addAttribute("userSearchDto", userSearchDto);
        return FORM_PAGE_FOR_QUERY_INPUT;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("userSearchDto")UserSearchDto userSearchDto,
            BindingResult result, SessionStatus status,
            Model model)
    {
        List<UserSearchResultDto> userSearchResultList = new ArrayList<UserSearchResultDto>();

        fillBuyersInfo(userSearchDto, userSearchResultList);
        fillSellersInfo(userSearchDto, userSearchResultList);

        request.setAttribute("userSearchResultList", userSearchResultList);

        return FORM_PAGE_FOR_QUERY_RESULT;
    }

    private void fillSellersInfo(UserSearchDto userSearchDto, List<UserSearchResultDto> userSearchResultList)
    {
        List<Seller> sellerList = sellerService.findSellerBySearchCriteria(userSearchDto);
        for(Seller seller : sellerList){
            UserSearchResultDto searchResultDto = new UserSearchResultDto();
            searchResultDto.fillDataWith(seller);
            userSearchResultList.add(searchResultDto);
        }
    }

    private void fillBuyersInfo(UserSearchDto userSearchDto, List<UserSearchResultDto> userSearchResultList)
    {
        List<User> userList = userService.findUserBySearchCriteria(userSearchDto);
        for(User user : userList){
            UserSearchResultDto searchResultDto = new UserSearchResultDto();
            searchResultDto.fillDataWith(user);
            userSearchResultList.add(searchResultDto);
        }
    }
}
