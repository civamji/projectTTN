package security.oauth.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

@Service
public class UserEmailToken {

    @Autowired
    TokenStore tokenStore;

    public String getUserEmail(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String tokenValue = authHeader.replace("Bearer", "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
        LinkedHashMap<String,String> userMap = new LinkedHashMap<>();
        try {
            userMap = (LinkedHashMap<String, String>) authentication.getUserAuthentication().getDetails();
        } catch (ClassCastException ex) {

        }
        String userEmail = new String();
        try {
            userEmail = userMap.get("username");
        } catch (NullPointerException e) {
        }
        return userEmail;
    }

}
