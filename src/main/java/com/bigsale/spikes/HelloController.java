package com.bigsale.spikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/08/12
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */

public class HelloController implements Controller {
    @Autowired
    HelloSpring helloSpring;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String name = request.getParameter("name");

        String message = helloSpring.sayHello(name);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);

        return new ModelAndView("hello", model);
    }
}
