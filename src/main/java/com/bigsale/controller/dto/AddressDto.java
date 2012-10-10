package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddressDto {
    @Setter
    @Getter
    Map<String, String> stateMap;

    public AddressDto()
    {
        stateMap = new HashMap<String, String>();
        stateMap.put("ACT", "ACT");
        stateMap.put("JBT", "JBT");
        stateMap.put("NSW", "NSW");
        stateMap.put("NT", "NT");
        stateMap.put("QLD", "QLD");
        stateMap.put("SA", "SA");
        stateMap.put("TAS", "TAS");
        stateMap.put("VIC", "VIC");
        stateMap.put("WA", "WA");

    }

    @Getter
    @Setter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 4, max = 20, message = "Please enter between {1} and {2} characters.")

    private String street;

    @Getter
    @Setter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 2, max = 20, message = "Please enter between {1} and {2} characters.")
    private String city;

    @Getter
    @Setter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 2, max = 3, message = "Please Select State.")
    private String state;


    @Getter
    @Setter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 4, max = 4, message = "Please enter {1} characters.")
    private String zipcode;
}
