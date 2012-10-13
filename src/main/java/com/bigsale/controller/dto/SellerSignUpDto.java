package com.bigsale.controller.dto;

import com.bigsale.service.SellerService;
import com.bigsale.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SellerSignUpDto {
    Logger logger = LoggerFactory.getLogger(SellerSignUpDto.class);
    private SellerService sellerService;
    private UserService userService;

    @Autowired
    public SellerSignUpDto(UserService userService, SellerService sellerService)
    {
        this.userService = userService;
        this.sellerService = sellerService;
    }
    public void isPasswordInputMatched(){
        if(password.equals(passwordConfirm)){
            isPasswordInputMatched = 0;
        }
        else{
            isPasswordInputMatched = -1;
        }
    }

    public void isIdDuplicated(){
        boolean result = sellerService.checkIdDuplication(sellerId)
                        ||userService.checkIdDuplication(sellerId);

        if(result == true){
            isIdDuplicated = -1; //duplicated
        }
        else{
            isIdDuplicated = 0; //not duplicated
        }
    }

    @Setter
    @Getter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 2, max = 20, message = "Please enter between {1} and {2} characters.")
    private String sellerId;

    @Setter
    @Getter
    @Min(value = 0, message = "ID Duplicated ")
    private int isIdDuplicated;

    @Setter
    @Getter
    @NotBlank(message = "Please enter your name.")
    @Length(min = 2, max = 30, message = "Please enter between {1} and {2} characters.")
    private String fullName;

    @Setter
    @Getter
    @NotBlank(message = "Please enter your Password.")
    @Length(min = 4, max = 40, message = "Please enter between {1} and {2} characters.")
    private String password;

    @Setter
    @Getter
    @NotBlank(message = "Please Confirm your Password.")
    @Length(min = 4, max = 40, message = "Please enter between {1} and {2} characters.")
    private String passwordConfirm;

    @Setter
    @Getter
    @Min(value = 0, message = "Password Not Match ")
    private int isPasswordInputMatched;

    @Setter
    @Getter
    @Email(message = "Please enter a valid e-mail address.")
    @Length(min = 4, max = 40, message = "Please enter between {1} and {2} characters.")
    private String email;
}
