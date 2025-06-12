package com.Lect.week10;

import com.Lect.week09.RegisterRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRequestValidator implements Validator {
    private static final String regExp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private Pattern pattern;

    public RegisterRequestValidator() {
        this.pattern = Pattern.compile(regExp);
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.isAssignableFrom(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) { // 매개변수 검정할 객체 , Errors 객체
        RegisterRequest regReq = (RegisterRequest) target;
        if (regReq.getEmail() == null || !regReq.getEmail().contains("@")) {
            errors.rejectValue("email", "email.invalid");
        }
        Matcher matcher = pattern.matcher(regReq.getPassword());
        if (!matcher.matches()) {
            errors.rejectValue("password", "password.bad");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty","name is required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "confirmPassword.required");
        if(!regReq.getPassword().isEmpty()){
            if (!regReq.isPasswordEqualToConfirmPassword()) {
                errors.rejectValue("confirmPassword", "confirmPassword.nomatch");
            }}
        }

    }


