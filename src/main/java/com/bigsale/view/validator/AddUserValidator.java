package com.bigsale.view.validator;

import com.bigsale.orm.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AddUserValidator implements Validator {

    @Override
    public boolean supports(Class clazz)
    {
        //just validate the User instances
        return User.class.isAssignableFrom(clazz);
    }

    //validate page 1, userName
    public void validatePage1Form(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId",
                "required.userId", "Field name is required.");
    }

    //validate page 2, password
    public void validatePage2Form(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");
    }


    @Override
    public void validate(Object target, Errors errors)
    {
        validatePage1Form(target, errors);
        validatePage2Form(target, errors);
    }
}
