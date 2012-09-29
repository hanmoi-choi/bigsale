package com.bigsale.controller.user;

import com.bigsale.orm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/addUser")
public class AdminAddUserFormController {

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("user")User user, BindingResult result){
        System.out.println(user);
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(ModelMap modelMap){
        Map<Integer, String> stateOnAustralia = new LinkedHashMap<Integer, String>();
        modelMap.addAttribute("stateOnAustralia", stateOnAustralia);

        return "addUser";
    }
}
