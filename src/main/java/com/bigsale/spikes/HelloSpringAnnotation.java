package com.bigsale.spikes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 2/09/12
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HelloSpringAnnotation {
    @Autowired
    HelloSpring helloSpring;

    static final Logger logger = LoggerFactory.getLogger(HelloSpringAnnotation.class);

    @RequestMapping("/hello1")
    public String hello(@RequestParam("name") String name, ModelMap model){
        model.put("message", helloSpring.sayHello(name));
        logger.debug("Test");

        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello2(@RequestParam("name") String name, ModelMap model){
        model.put("message", helloSpring.sayHello(name + "^^"));

        return "hello";
    }

    @RequestMapping("/")
    public String index(){

        logger.warn("Succeed");
        return "/index.jsp";
    }
}
