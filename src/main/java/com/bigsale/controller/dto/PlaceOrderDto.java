package com.bigsale.controller.dto;

import com.bigsale.orm.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Max;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PlaceOrderDto {

    @Getter
    @Setter
    private String itemName;

    @Getter
    @Setter
    private int stockQuantity;

    @Getter
    @Setter
    @Min(value = 0, message = "Stock is not Enough")
    private int isOrderAmountGreaterThanStock = 0;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    private int discountRate;

    @Getter
    @Setter
    @Min(value = 1, message = "Order Amount shall be bigger Than {1}")
    @Max(value = 9999, message = "Amount Shall be less than {1}")
    private int orderAmount;

    @Getter
    @Setter
    private int totalPrice;

    public void checkStockAmount(){
        if(orderAmount > stockQuantity){
            isOrderAmountGreaterThanStock = -1;
        }
        else{
            isOrderAmountGreaterThanStock = 0;
        }
    }

    public void fiilWith(Item item)
    {
        itemName = item.getItemName();
        stockQuantity = item.getStockQuantity();
        price = item.getPrice();
        discountRate = item.getDiscountRate();
    }

    public void calculateTotalPrice(){
        totalPrice = (price * orderAmount) - (price * discountRate);
    }
}
