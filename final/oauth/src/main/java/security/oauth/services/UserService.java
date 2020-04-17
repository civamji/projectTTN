package security.oauth.services;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import security.oauth.custom_validators.EmailValidator;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.entities.Customer;
import security.oauth.entities.Seller;
import security.oauth.entities.User;
import security.oauth.entities.VerificationToken;
import security.oauth.repos.*;
import security.oauth.services.CustomerService;
import security.oauth.services.EmailService;
import security.oauth.services.SellerService;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerService sellerService;

    @Autowired
    EmailService emailService;

    @Autowired
    MessageSource messages;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    private TokenStore tokenStore;



    public String createVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }



    public VerificationToken getVerificationToken(final User user) {
        return verificationTokenRepository.findByUser(user);
    }


    public VerificationToken getVerificationToken(final String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }






//    public int checkVerificationTokenValidity(String token){
//        VerificationToken verificationToken = getVerificationToken(token);
//        if (verificationToken == null) {
//            // token not found.
//            return 0;
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            // token expired.
//            return -1;
//        }
//        return 1;
//    }

//    User getUser(String verificationToken){
//        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
//        return token.getUser();
//    }
//
    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
        System.out.println("save sucess!!");
    }

    // activate any user - customer or seller


    public ResponseEntity activateUserById(Long id, WebRequest request) {
        Optional<User> user = userRepository.findById(id);
        ResponseEntity responseEntity;

        String message, error;

        if(!user.isPresent()){
            error = "No resource found";
            message = "No user found with this Id";
         //   response = new ErrorVariationOrder(error, message, new Date());
            responseEntity = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        else{
            User savedUser = user.get();
            if(savedUser.isActive()){
                message = "User already active";
            }
            else{
                savedUser.setActive(true);
                userRepository.save(savedUser);
//                Locale locale = request.getLocale();
                String subject = "Account Activation";
                String emailMessage = "Your account has been activated successfully by our team.";
                emailService.sendEmail(savedUser.getEmail(), subject, emailMessage);

                message = "User has been activated";
            }
//            response = new ResponseVariationOrder<>(null, message, new Date());
            responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
        }
        return responseEntity;
    }


    public boolean isValidEmail(String email){
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user==null)
            System.out.println("user not found");

        return user;
    }


//    public User updateProfile(CustomerProfileDto customerDto) {
//      User user=userRepository.g
//
//    }
}
