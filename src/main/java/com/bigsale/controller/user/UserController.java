package com.bigsale.controller.user;

import com.bigsale.orm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 3:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes
public class UserController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("user")
                                 User user, BindingResult result) {

        return "redirect:contacts.html";
    }


}

