package com.bigsale.controller.user;

import com.bigsale.orm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class AdminController{

    static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/addSellers")
    public ModelAndView addSellers() {
        logger.debug("addSellers");
        return new  ModelAndView("/admin/addSeller1stForm", "user", new User());
    }
}
