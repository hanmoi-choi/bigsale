package com.bigsale.controller.dto;

import com.bigsale.orm.model.Item;
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
public class ItemSearchResultDto {

    @Setter
    @Getter
    private int itemId;

    @Setter
    @Getter
    private String itemName;

    @Setter
    @Getter
    private int stockAmount;

    @Setter
    @Getter
    private int price;

    @Setter
    @Getter
    private int discountRate;

    @Setter
    @Getter
    private String description;

    public void fillWith(Item item)
    {
        itemId = item.getItemId();
        itemName = item.getItemName();
        stockAmount = item.getStockQuantity();
        price = item.getPrice();
        discountRate = item.getDiscountRate();
    }
}
