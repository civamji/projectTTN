package security.oauth.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import security.oauth.services.UserService;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

//    @GetMapping("/user/home")
//    public ResponseEntity userHome(){
//        String data = "user home";
//
//    }
}