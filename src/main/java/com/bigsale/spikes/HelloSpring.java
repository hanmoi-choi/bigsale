package com.bigsale.spikes;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 29/08/12
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */

@Component
public class HelloSpring {
    public String sayHello(String name)
    {
        return "Hello " + name;
    }
}
