package security.oauth.controllers;
//package security.oauth.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.dtos.SellerProfileDto;
import security.oauth.services.SellerService;


import javax.validation.Valid;
import java.util.Date;

@RestController
public class SellerController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private SellerService sellerService;



    @GetMapping(path = "/getSellerDetails/{user_id}")
    public SellerProfileDto getCustomerProfile(@PathVariable("user_id") Long user_id){
        return sellerService.getSellerDetials(user_id);
    }


    @PutMapping(path = "/profile-update/{user_id}")
    public String updateProfile(@Valid @RequestBody SellerProfileDto profileDto, @PathVariable("user_id") Long user_id){
        return sellerService.updateSeller(profileDto,user_id);
    }




//    @GetMapping("/seller/home")
//    public ResponseEntity<ResponseVariationOrder> getsellerHome(){
//        String data = "seller home";
//        ResponseVariationOrder<String> response = new ResponseVariationOrder<>(data, null, new Date());
//        return new ResponseEntity<ResponseVariationOrder>(response, HttpStatus.OK);
//    }
//
 }