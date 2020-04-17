//package security.oauth.services;
//
//import org.aspectj.weaver.ast.Var;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.request.WebRequest;
//import security.oauth.custom_validators.EmailValidator;
//import security.oauth.dtos.CustomerRegistration;
//import security.oauth.dtos.SellerRegistration;
//import security.oauth.entities.Customer;
//import security.oauth.entities.Seller;
//import security.oauth.entities.User;
//import security.oauth.entities.VerificationToken;
//import security.oauth.repos.*;
//import security.oauth.services.CustomerService;
//import security.oauth.services.EmailService;
//import security.oauth.services.SellerService;
//
//
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Service
//@Transactional
//public class UserService {
//
//    @Autowired
//    VerificationTokenRepository verificationTokenRepository;
//
//    @Autowired
//    ForgotPasswordRepository forgotPasswordRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    CustomerRepository customerRepository;
//
//    @Autowired
//    CustomerService customerService;
//
//    @Autowired
//    SellerRepository sellerRepository;
//
//    @Autowired
//    SellerService sellerService;
//
//    @Autowired
//    EmailService emailService;
//
//    @Autowired
//    MessageSource messages;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    EmailValidator emailValidator;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    public String createVerificationToken(User user){
//        String token = UUID.randomUUID().toString();
//        VerificationToken verificationToken = new VerificationToken(token, user);
//        verificationTokenRepository.save(verificationToken);
//        return token;
//    }
//
//    public String createForgotPasswordToken(User user){
//        String token = UUID.randomUUID().toString();
//        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken(token, user);
//        forgotPasswordRepository.save(forgotPasswordToken);
//        return token;
//    }
//
//    public VerificationToken getVerificationToken(final User user) {
//        return verificationTokenRepository.findByUser(user);
//    }
//
//    public VerificationToken getVerificationToken(final String VerificationToken) {
//        return verificationTokenRepository.findByToken(VerificationToken);
//    }
//
//    public ForgotPasswordToken getForgotPasswordToken(final User user) {
//        return forgotPasswordRepository.findByUser(user);
//    }
//
//    public ForgotPasswordToken getForgotPasswordToken(final String forgotPasswordToken) {
//        return forgotPasswordRepository.findByToken(forgotPasswordToken);
//    }
//
//    public VariationOrderid deleteVerificationToken(String token){
//        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
//        verificationTokenRepository.delete(verificationToken);
//    }
//
//    public VariationOrderid deleteForgotPasswordToken(String token){
//        ForgotPasswordToken forgotPasswordToken = forgotPasswordRepository.findByToken(token);
//        forgotPasswordRepository.delete(forgotPasswordToken);
//    }
//
////    public int checkVerificationTokenValidity(String token){
////        VerificationToken verificationToken = getVerificationToken(token);
////        if (verificationToken == null) {
////            // token not found.
////            return 0;
////        }
////
////        User user = verificationToken.getUser();
////        Calendar cal = Calendar.getInstance();
////        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
////            // token expired.
////            return -1;
////        }
////        return 1;
////    }
//
//    User getUser(String verificationToken){
//        VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
//        return token.getUser();
//    }
//
//    public VariationOrderid saveRegisteredUser(final User user) {
//        userRepository.save(user);
//    }
//
//    // activate any user - customer or seller
//    public ResponseEntity activateUserById(Long id, WebRequest request) {
//        Optional<User> user = userRepository.findById(id);
//        ResponseEntity responseEntity;
//
//        String message, error;
//
//        if(!user.isPresent()){
//            error = "No resource found";
//            message = "No user found with this Id";
//         //   response = new ErrorVariationOrder(error, message, new Date());
//            responseEntity = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//        }
//        else{
//            User savedUser = user.get();
//            if(savedUser.isActive()){
//                message = "User already active";
//            }
//            else{
//                savedUser.setActive(true);
//                userRepository.save(savedUser);
////                Locale locale = request.getLocale();
//                String subject = "Account Activation";
//                String emailMessage = "Your account has been activated successfully by our team.";
//                emailService.sendEmail(savedUser.getEmail(), subject, emailMessage);
//
//                message = "User has been activated";
//            }
////            response = new ResponseVariationOrder<>(null, message, new Date());
//            responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
//        }
//        return responseEntity;
//    }
//
//    // de-activate any user - customer or seller
//    public ResponseEntity<VariationOrder> deactivateUserById(Long id, WebRequest request) {
//        Optional<User> user = userRepository.findById(id);
//        ResponseEntity<VariationOrder> responseEntity;
//        VariationOrder response;
//        String message, error;
//
//        if(!user.isPresent()){
//            error = "No resource found";
//            message = "No user found with this Id";
//            response = new ErrorVariationOrder(error, message, new Date());
//            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        else{
//            User savedUser = user.get();
//            if(!savedUser.isActive()){
//                message = "User already inactive";
//            }
//            else{
//                savedUser.setActive(false);
//                userRepository.save(savedUser);
////                Locale locale = request.getLocale();
//
//                String emailMessage = "your account has been de-activated.";
//                String subject = "Account De-activation";
//                emailService.sendEmail(savedUser.getEmail(), subject, emailMessage);
//
//                message = "User has been deactivated.";
//            }
//            response = new ResponseVariationOrder<>(null, message, new Date());
//            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        return responseEntity;
//    }
//
//    public boolean isValidEmail(String email){
//        final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
//        Pattern pattern;
//        Matcher matcher;
//
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    public User getUserByEmail(String email){
//        User user = userRepository.findByEmail(email);
//
//        if(user==null)
//            System.out.println("user not found");
//
//        return user;
//    }
//
//    public VariationOrderid logoutUser(String email, WebRequest request){
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            String tokenValue = authHeader.replace("Bearer", "").trim();
//            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//            tokenStore.removeAccessToken(accessToken);
//        }
//    }
//
//    public VariationOrderid registerUnsuccessfulLogin(User user){
//        int count = user.getFailedAttempts();
//        user.setFailedAttempts(count+1);
//        if(user.getFailedAttempts() >= 3){
//            user.setLocked(true);
//            sendAccountLockingMail(user.getEmail());
//        }
//    }
//
//    private VariationOrderid sendAccountLockingMail(String email) {
//        String subject = "Account Locked";
//        String message = "your account has been locked due to multiple unsuccessful login attempts.";
//        emailService.sendEmail(email, subject, message);
//    }
//
//    public VariationOrderid registerSuccessfulLogin(User user){
//        user.setFailedAttempts(0);
//    }
//
//    public VariationOrderid sendActivationLinkMail(String appUrl, User user, Locale locale, String subject){
//
//        // create and save the token
//        String token = createVerificationToken(user);
//
//        // prepare the email contents
//        String email = user.getEmail();
//        String confirmationUrl = "http://localhost:8080" + appUrl +
//                "/activate/customer?token=" + token;
//
////        String message = messages.getMessage("message.regSucc", null, locale);
//        String message = "please activate your account \r\n" + confirmationUrl;
//        System.out.println(confirmationUrl);
//
//        // populate the email and send
//        emailService.sendEmail(email, subject, message);
//    }
//
//    public VariationOrderid sendForgotPasswordInitiationMail(User user, String token){
//
//        String email = user.getEmail();
//        String subject = "Password Reset Link";
//        String passwordResetUrl = "http://localhost:8080" + "/reset-password?token=" + token;
//        String emailMessage = "please click on this link to reset your password";
//        String emailBody = emailMessage + "\r\n" + passwordResetUrl;
//        System.out.println(passwordResetUrl);
//
//        emailService.sendEmail(email, subject, emailBody);
//    }
//
//    public VariationOrderid sendPasswordResetConfirmationMail(String email) {
//        String subject = "Password Reset Successfully";
//        String message = "the password for yout account has been reset successfully";
//        emailService.sendEmail(email, subject, message);
//    }
//
//    public ResponseEntity<VariationOrder> initiatePasswordReset(String email, WebRequest request){
//        String message, error;
//        VariationOrder response;
//
//        // check uniqueness of email
//        User user = userRepository.findByEmail(email);
//        if(user==null)
//            throw new UsernameNotFoundException("This email address does not exist.");
//
//        else if(!user.isActive() || user.isLocked()) {
//            message = "User is either de-activated or locked";
//            error = "Operation not allowed";
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//        }
//
//        // user is activated - eligible for this operation.
//        String token =createForgotPasswordToken(user);
//        sendForgotPasswordInitiationMail(user, token);
//        message = messages.getMessage("message.resetPasswordEmail", null, request.getLocale());
//        response = new ResponseVariationOrder<String>(null, message, new Date());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    public ResponseEntity<VariationOrder> createNewCustomer(CustomerRegistration customerDto, WebRequest request){
//        Customer customer = customerRepository.findByEmail(customerDto.getEmail());
//
//        if(customer != null)
//            throw new EmailAlreadyExistsException("email id already exists");
//
//        Customer newCustomer = customerService.toCustomer(customerDto);
//        newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
//        Customer savedCustomer = customerRepository.save(newCustomer);
//
//        String appUrl = request.getContextPath();
//        sendActivationLinkMail(appUrl, savedCustomer, request.getLocale(), "Registration Confirmation");
//
//        String message = "Account created successfully. An activation mail has been sent to your email id.";
//        VariationOrder response = new ResponseVariationOrder<>(null, message, new Date());
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    public ResponseEntity<VariationOrder> createNewSeller(SellerRegistration sellerRegistrationDto) {
//        String message = sellerService.getUniquenessStatus(sellerRegistrationDto);
//        String error;
//        VariationOrder response;
//        if(!message.equals("unique")){
//            error = "Invalid attributes.";
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<VariationOrder>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        Seller seller = sellerService.toSeller(sellerRegistrationDto);
//        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
//        sellerRepository.save(seller);
//        sellerService.sendAcknowledgementMail(seller.getEmail());
//        message = "Account created successfully. It will be activated after verification.";
//        response = new ResponseVariationOrder<>(null, message, new Date());
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    public ResponseEntity<VariationOrder> resendActivationLink(String email, WebRequest request) {
//        User user = userRepository.findByEmail(email);
//        String appUrl = request.getContextPath();
//        Locale locale = request.getLocale();
//        String error, message;
//        VariationOrder response;
//
//        if(user==null){
//            error = messages.getMessage("ValidEmail.user.email", null, locale);
//            message = "No user found with this email address.";
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<VariationOrder>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        VerificationToken token = getVerificationToken(user);
//        if(token==null){
//            message = "User already activated";
//            response = new ResponseVariationOrder<String>(null, message, new Date());
//        }
//
//        deleteVerificationToken(token.getToken());
//        sendActivationLinkMail(appUrl, user, locale, "Account Activation Link");
//        message = messages.getMessage("message.resendToken", null, locale);
//        response = new ResponseVariationOrder<String>(null, message, new Date());
//
//        return new ResponseEntity<VariationOrder>(response, HttpStatus.OK);
//    }
//
//    public ResponseEntity<VariationOrder> activateUserByToken(String token, WebRequest request){
//        Locale locale = request.getLocale();
//        String message;
//        String error;
//        VariationOrder response;
//
//        // if token doesn't exist in database
//        VerificationToken verificationToken = getVerificationToken(token);
//        if (verificationToken == null) {
//            error = messages.getMessage("auth.message.invalidToken", null, locale);
//            message = "No user found with given token.";
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<VariationOrder>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        // if token is expired
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//
//            error = messages.getMessage("auth.message.expired", null, locale);
//            message = "Your activation link has been expired. We have sent a net link to your " +
//                    "registered email.";
//            response = new ErrorVariationOrder(error, message, new Date());
//
//            String appUrl = request.getContextPath();
//            deleteVerificationToken(token);
//            sendActivationLinkMail(appUrl, user, locale, "Account Activation Link");
//
//            return new ResponseEntity<VariationOrder>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        // if everything is alright
//        if(user.isActive()){
//            message = "Your account is already active";
//            response = new ResponseVariationOrder<String>(null, message, new Date());
//            return new ResponseEntity<VariationOrder>(response, HttpStatus.OK);
//        }
//
//        user.setActive(true);
//        saveRegisteredUser(user);
//        deleteVerificationToken(token);
//        message = "you have been activated successfully";
//        response = new ResponseVariationOrder<String>(null, message, new Date());
//        return new ResponseEntity<VariationOrder>(response, HttpStatus.OK);
//    }
//
//    public ResponseEntity<VariationOrder> resetPassword(String token, ForgotPassword passwords, WebRequest request){
//        Locale locale = request.getLocale();
//        String message, error;
//        VariationOrder response;
//
//        // if token doesn't exist in database
//        ForgotPasswordToken forgotPasswordToken = getForgotPasswordToken(token);
//        if (forgotPasswordToken == null) {
//            error = messages.getMessage("auth.message.invalidToken", null, locale);
//            message = "The token provided by you doesn't correspond to any user.";
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        // if token is expired
//        User user = forgotPasswordToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((forgotPasswordToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//
//            String appUrl = request.getContextPath();
//            deleteForgotPasswordToken(token);
//            error = "Token expired.";
//            message = messages.getMessage("auth.message.expired", null, locale);
//            response = new ErrorVariationOrder(error, message, new Date());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        user.setPassword(passwords.getPassword());
//        saveRegisteredUser(user);
//        deleteForgotPasswordToken(token);
//
//        // logout the user of all active sessions, if any - delete the oAuth token
//        logoutUser(user.getEmail(), request);
//        sendPasswordResetConfirmationMail(user.getEmail());
//        message = "Password changed successfully.";
//        response = new ResponseVariationOrder<>(null, message, new Date());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}