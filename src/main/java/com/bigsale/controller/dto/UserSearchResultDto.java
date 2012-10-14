package com.bigsale.controller.dto;

import com.bigsale.orm.model.Seller;
import com.bigsale.orm.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 12/10/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserSearchResultDto {
    @Setter
    @Getter
    private String userId;

    @Setter
    @Getter
    private String fullName;

    @Setter
    @Getter
    private String email;

    @Getter
    @Setter
    private String level;

    @Setter
    @Getter
    private String type;

    public void fillDataWith(User user){
        userId = user.getUserId();
        fullName = user.getFullName();
        email = user.getEmail();
        level = user.getUserLevel().toString();
        type = "Buyer";

    }

    public void fillDataWith(Seller seller){
        userId = seller.getSellerId();
        fullName = seller.getFullName();
        email = seller.getEmail();
        level = seller.getSellerLevel().toString();
        type = "Seller";
    }
}
