package com.example.zktraining.util;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ValidForm extends AbstractValidator {

    @Override
    public void validate(ValidationContext validationContext) {
        Map<String, Property> beanProps = validationContext.getProperties(validationContext.getProperty().getBase());
        validateName(validationContext, (String)beanProps.get("name").getValue());
    }

    private void validateName(ValidationContext ctx, String email) {
        if(!email.matches("\\w+")) {
            this.addInvalidMessage(ctx, "name", "Tên xe không được chứa ký tự đặc biệt!");
        }
    }

}
