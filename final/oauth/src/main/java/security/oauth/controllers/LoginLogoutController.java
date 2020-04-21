package security.oauth.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import security.oauth.entities.ForgotPasswordToken;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class LoginLogoutController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    MessageSource messages;


    @GetMapping("/")
    public String index() {
        return "index";
    }
}



//}