package com.bigsale.controller.user;

import com.bigsale.orm.model.Address;
import com.bigsale.orm.model.User;
import com.bigsale.view.validator.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/09/12
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/addSellerForm")
@SessionAttributes({"user", "address"})
public class AdminAddUserFormController {
    private AddUserValidator addUserValidator;

    @Autowired
    public AdminAddUserFormController(AddUserValidator addUserValidator) {
        this.addUserValidator = addUserValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        User user = new User();
        user.setFirstName("Hanmoi");
        Address address = new Address();
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "/admin/addSellerFormPageOne";
    }

//    @RequestMapping(value = "addSeller",method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("user") User user,
                                 @ModelAttribute("address") Address address,
                                 BindingResult result){

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "hanmoi");
        return new ModelAndView("/admin/addConfirmed", model);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String initializeForm(ModelMap modelMap){
//        Map<Integer, String> stateOnAustralia = new LinkedHashMap<Integer, String>();
//        modelMap.addAttribute("stateOnAustralia", stateOnAustralia);
//
//        return "addUser";
//    }
}
