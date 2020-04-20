package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.services.ForgotPasswordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/forgot/password")
public class ForgotPasswordController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    @GetMapping(path = "/check")
    public String check(){
        return "Module Active!!";
    }

    @PostMapping("/token/{email}")
    public String getToken(@PathVariable String email, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String getMessage = forgotPasswordService.sendToken(email);
        System.out.println(getMessage);
        if (getMessage.equals("Success")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);
            }
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PatchMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String token, @RequestParam String pass, @RequestParam String cpass, HttpServletResponse httpServletResponse) {
        String getMessage = forgotPasswordService.resetPassword(email, token, pass, cpass);
        if (getMessage.equals("Password Changed Successfully")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

}
