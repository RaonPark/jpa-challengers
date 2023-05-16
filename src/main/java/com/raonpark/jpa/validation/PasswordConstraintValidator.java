package com.raonpark.jpa.validation;

import java.util.Arrays;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RepeatCharacterRegexRule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // PasswordValidator는 passay에 있음. baeldung의 코드를 최신버전의 passay를 사용하여 바꿈
        // https://www.baeldung.com/registration-password-strength-and-rules
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 15),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new RepeatCharacterRegexRule(3),
            new WhitespaceRule()
        ));

        RuleResult result = passwordValidator.validate(new PasswordData(value));
        if(result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        // Guava Joiner를 사용하여 리스트를 스트링으로 묶음
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(passwordValidator.getMessages(result))).addConstraintViolation();
        return false;
    }
    
}
