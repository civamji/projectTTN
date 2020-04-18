package security.oauth.custom_validators;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidation {
    private static final String pattern = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!]).{8,15})";

    public boolean isValid(String pass){
        return pass.matches(pattern);
    }

    public boolean validatePassword(String pass, String cpass ) {
        if (pass.equals(cpass)){
            if (pass.matches(pattern)) {
                if (cpass.matches(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean validPassword(String oldPass,String pass, String cpass ) {
        if (oldPass.matches(pattern)) {
            if (pass.equals(cpass)) {
                if (pass.matches(pattern)) {
                    if (cpass.matches(pattern)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


