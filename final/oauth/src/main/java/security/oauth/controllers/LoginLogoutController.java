//package security.oauth.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//import security.oauth.entities.ForgotPasswordToken;
//import security.oauth.services.UserService;
//import security.oauth.utils.ResponseVariationOrder;
//import security.oauth.utils.VariationOrder;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.Date;
//
//@RestController
//public class LoginLogoutController {
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    MessageSource messages;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }
//
//
//    @PostMapping("/doLogout")
//    public ResponseEntity<VariationOrder> logout(HttpServletRequest request){
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            String tokenValue = authHeader.replace("Bearer", "").trim();
//            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//            tokenStore.removeAccessToken(accessToken);
//        }
//        VariationOrder response = new ResponseVariationOrder<>(null, "You have been logged out successfully", new Date());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<VariationOrder> getResetPasswordToken(@RequestBody String email, WebRequest request){
//        return userService.initiatePasswordReset(email, request);
//    }
//
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<VariationOrder> resetPassword(@RequestParam("token") String token, @Valid @RequestBody ForgotPasswordToken passwords, WebRequest request){
//        return userService.resetPassword(token, passwords, request);
//    }
//}