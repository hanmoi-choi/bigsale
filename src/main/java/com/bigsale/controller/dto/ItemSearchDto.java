package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 14/10/12
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ItemSearchDto {

    @Getter
    @Setter
    private String itemName;
}
