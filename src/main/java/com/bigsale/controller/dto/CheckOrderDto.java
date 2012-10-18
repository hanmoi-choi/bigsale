package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 15/10/12
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckOrderDto {
    @Getter
    @Setter
    private int orderId;

    @Getter
    @Setter
    private String itemName;

    @Getter
    @Setter
    private int orderAmount;

    @Getter
    @Setter
    private int totalPrice;

    @Getter
    @Setter
    private String deliveryStatus;
}
