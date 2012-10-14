package com.bigsale.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 11/10/12
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SellerModifyDto {
    Logger logger = LoggerFactory.getLogger(SellerModifyDto.class);

    @Setter
    @Getter
    @NotBlank(message = "Please enter your ID.")
    @Length(min = 2, max = 20, message = "Please enter between {1} and {2} characters.")
    private String sellerId;

    @Setter
    @Getter
    @NotBlank(message = "Please enter your name.")
    @Length(min = 2, max = 30, message = "Please enter between {1} and {2} characters.")
    private String fullName;

    @Setter
    @Getter
    @Email(message = "Please enter a valid e-mail address.")
    @Length(min = 4, max = 40, message = "Please enter between {1} and {2} characters.")
    private String email;
}
