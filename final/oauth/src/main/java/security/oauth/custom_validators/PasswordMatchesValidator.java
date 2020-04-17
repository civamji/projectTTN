package security.oauth.custom_validators;

import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.dtos.SellerRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){

        if(obj instanceof SellerRegistrationDto){
            SellerRegistrationDto seller = (SellerRegistrationDto) obj;
            return seller.getPassword().equals(seller.getConfirmPassword());
        }
//        else if(obj instanceof ForgotPasswordToken){
//            ForgotPasswordToken passwords = (ForgotPasswordToken) obj;
//            return passwords.getPassword().equals(passwords.getConfirmPassword());
//        }

        CustomerRegistrationDto customer = (CustomerRegistrationDto) obj;
        return customer.getPassword().equals(customer.getConfirmPassword());
    }
}