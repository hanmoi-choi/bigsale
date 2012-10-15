package com.bigsale.controller.dto;

import com.bigsale.orm.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Max;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ItemAddDto {
    @Setter
    @Getter
    @NotBlank(message = "Please enter your Item Name.")
    @Length(min = 1, max = 40, message = "Please enter between {1} and {2} characters.")
    private String itemName;

    @Setter
    @Getter
    @Min(value = 1, message = "Amount Shall be greater than {1}")
    @Max(value = 9999, message = "Amount Shall be less than {1}")
    private int stockQuantity;

    @Setter
    @Getter
    @Min(value = 1, message = "Amount Shall be greater than Zero")
    private int price;

    @Setter
    @Getter
    @Min(value = 0, message = "Discount Rate Shall be positive number")
    @Max(value = 99, message = "Amount Shall be less than {1}")
    private int discountRate;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String picturePath;

    public void fiilWith(Item item)
    {
        itemName = item.getItemName();
        stockQuantity = item.getStockQuantity();
        price = item.getPrice();
        discountRate = item.getDiscountRate();
        description = item.getDescription();
    }
}
