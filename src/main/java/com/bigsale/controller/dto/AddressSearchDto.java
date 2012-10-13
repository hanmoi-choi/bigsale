package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddressSearchDto {

    @Getter
    @Setter
    private String street;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String state;

    @Getter
    @Setter
    private String zipcode;
}
