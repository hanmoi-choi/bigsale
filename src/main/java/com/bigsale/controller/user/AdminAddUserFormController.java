package com.bigsale.controller.user;

import com.bigsale.orm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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
public class AdminAddUserFormController {

    @RequestMapping(value = "addSeller",method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("user") User user, BindingResult result){
        System.out.println(user);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "hanmoi");
        return new ModelAndView("/admin/addConfirmed", model);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initializeForm(ModelMap modelMap){
        Map<Integer, String> stateOnAustralia = new LinkedHashMap<Integer, String>();
        modelMap.addAttribute("stateOnAustralia", stateOnAustralia);

        return "addUser";
    }
}
