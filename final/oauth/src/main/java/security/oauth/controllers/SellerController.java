//package security.oauth.controllers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import security.oauth.utils.ResponseVariationOrder;
//
//import java.util.Date;
//
//@RestController
//public class SellerController {
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @GetMapping("/seller/home")
//    public ResponseEntity<ResponseVariationOrder> getsellerHome(){
//        String data = "seller home";
//        ResponseVariationOrder<String> response = new ResponseVariationOrder<>(data, null, new Date());
//        return new ResponseEntity<ResponseVariationOrder>(response, HttpStatus.OK);
//    }
//}