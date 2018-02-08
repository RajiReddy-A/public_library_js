package io.public_library.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import io.public_library.auth.service.UserService;
import io.public_library.person.Person;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person user = (Person) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personName", "NotEmpty");
        if (user.getPersonName().length() < 6 || user.getPersonName().length() > 32) {
            errors.rejectValue("personName", "Size.userForm.personName");
        }
        if (userService.findOne(user.getPersonName()) != null) {
            errors.rejectValue("personName", "Duplicate.userForm.personName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
