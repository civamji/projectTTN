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
@RequestMapping(path = "/login-logout")
public class LoginLogoutController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    MessageSource messages;



    @GetMapping("/")
    public String index() {
        return "index";
    }

    //1 login as a selller

    //2 login as a customer

    //3.login as a admin

    //4. logutcustomer

    //5. logoutseller

    //6. logoutadmin


    @GetMapping("/doLogout")
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
        return "Logged out successfully";
    }
}



//}