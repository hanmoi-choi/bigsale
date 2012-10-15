package com.bigsale.controller.admin;

import com.bigsale.orm.model.Seller;
import com.bigsale.orm.model.User;
import com.bigsale.service.SellerService;
import com.bigsale.service.UserService;
import com.bigsale.util.AdminPdfReportView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/admin/makeReportForm")
public class AdminReportController extends AbstractController{

    @Autowired
    AdminPdfReportView adminPdfReportView;

    @Autowired
    UserService userService;

    @Autowired
    SellerService sellerService;

    static final Logger logger = LoggerFactory.getLogger(AdminReportController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//dummy data
		Map<String,Object> userData = new HashMap<String,Object>();

        List<User> userList = userService.getAllUsers();
        logger.debug("userList: {}", userList.size());

        List<Seller> sellerList = sellerService.getAllSellers();
        logger.debug("sellerList: {}", sellerList.size());

        userData.put("userList", userList);
        userData.put("sellerList", sellerList);

        return new ModelAndView(adminPdfReportView,"userData",userData);
	}

}
