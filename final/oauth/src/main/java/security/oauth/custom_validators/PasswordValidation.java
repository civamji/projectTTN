package security.oauth.custom_validators;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidation {
    private static final String pattern = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%!]).{8,15})";

    public boolean isValid(String pass){
        return pass.matches(pattern);
    }

    public boolean validatePassword(String password, String confirmpassword ) {
        if (password.equals(confirmpassword)){
            if (password.matches(pattern)) {
                if (confirmpassword.matches(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean validPassword(String oldPass,String password, String confirmpassword ) {
        if (oldPass.matches(pattern)) {
            if (password.equals(confirmpassword)) {
                if (password.matches(pattern)) {
                    if (confirmpassword.matches(pattern)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


