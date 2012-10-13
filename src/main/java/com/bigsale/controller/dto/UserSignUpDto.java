package com.bigsale.controller.dto;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserSignUpDto {
    Logger logger = LoggerFactory.getLogger(UserSignUpDto.class);


    UserService userService;

    @Setter
    @Getter
    Map<String, String> stateMap;


    @Autowired
    public UserSignUpDto(UserService userService)
    {
        this.userService = userService;
        logger.warn("{}", this.userService);
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

    public void isPasswordInputMatched(){
        if(password.equals(passwordConfirm)){
            isPasswordInputMatched = 0;
        }
        else{
            isPasswordInputMatched = -1;
        }
    }

    public void isIdDuplicated(){
        boolean result = userService.checkIdDuplication(userId);

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
    private String userId;

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
