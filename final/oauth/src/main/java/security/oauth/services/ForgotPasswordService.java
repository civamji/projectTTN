package security.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.oauth.custom_validators.EmailValidator;
import security.oauth.custom_validators.PasswordValidation;
import security.oauth.entities.ForgotPasswordToken;
import security.oauth.entities.User;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.ForgotPasswordRepository;
import security.oauth.repos.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;


@Service
public class ForgotPasswordService {

@Autowired
    EmailValidator emailValidator;

@Autowired
    UserRepository userRepository;

@Autowired
    EmailNotificationService emailNotificationService;

@Autowired
    PasswordValidation passwordValidation;
@Autowired
    ForgotPasswordRepository passwordRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String sendToken(String email) {
        boolean isValidEmail = emailValidator.validateEmail(email);
        if (!isValidEmail) {
            return "email is invalid";
        }
        User user = userRepository.findByEmail(email);
        try {
            if (user.getEmail().equals(null)) {
            }
        } catch (NullPointerException ex) {
            return "no email found";
        }

        String token = UUID.randomUUID().toString();

        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
        forgotPasswordToken.setToken(token);
        forgotPasswordToken.setExpiryDate(new Date());
        forgotPasswordToken.setUserEmail(email);

        passwordRepository.save(forgotPasswordToken);

        emailNotificationService.sendNotification("FORGOT PASSWORD", token, email);

        return "Success";
    }

    @Transactional
    public String resetPassword(String email, String token, String pass, String cpass) {
        if (!pass.equals(cpass)) {
            return "password and confirm password not match";
        }
        if (!passwordValidation.validatePassword(pass,cpass)) {
            return "invalid password syntax";
        }
        ForgotPasswordToken forgotPasswordToken = passwordRepository.findByUserEmail(email);
        try {
            if (forgotPasswordToken.getUserEmail().equals(null)) {
            }
        } catch (NullPointerException ex) {
            return "no email found";
        }
        Date date = new Date();
        long diff = date.getTime() - forgotPasswordToken.getExpiryDate().getTime();
        long diffHours = diff / (60 * 60 * 1000);
        if (diffHours > 24) {
            passwordRepository.deleteByUserEmail(email);
            return "Token has expired";
        }
        if (!forgotPasswordToken.getToken().equals(token)) {
            return "invalid token";
        }
        if (forgotPasswordToken.getToken().equals(token)) {
            User user = userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(pass));
            userRepository.save(user);
            passwordRepository.deleteByUserEmail(email);
            emailNotificationService.sendNotification("PASSWORD CHANGED", "YOUR PASSWORD HAS BEEN CHANGED", email);
            return "Success";
        }
        return "Password Changed Successfully";
    }
}

