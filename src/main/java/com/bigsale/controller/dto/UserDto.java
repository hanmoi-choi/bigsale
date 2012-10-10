package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springmodules.validation.bean.conf.loader.annotation.handler.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDto {

    @Setter
    @Getter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 2, max = 20, message = "Please enter between {1} and {2} characters.")
    private String userId;

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

    public void isPasswordInputMatched(){
        if(password.equals(passwordConfirm)){
            isPasswordInputMatched = 0;
        }
        else{
            isPasswordInputMatched = -1;
        }

    }
}
