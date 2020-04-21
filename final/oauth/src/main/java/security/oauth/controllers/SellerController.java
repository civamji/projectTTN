package security.oauth.controllers;
//package security.oauth.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.custom_validators.PasswordValidation;
import security.oauth.dtos.SellerProfileDto;
import security.oauth.services.SellerService;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping(path = "seller/home")
public class SellerController {

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private PasswordValidation passwordValidation;

//view profile

    @GetMapping(path = "/getSellerDetails/{user_id}")
    public SellerProfileDto getCustomerProfile(@PathVariable("user_id") Long user_id){
        return sellerService.getSellerDetials(user_id);
    }


    //update profile

    @PutMapping(path = "/profile-update/{user_id}")
    public String updateProfile(@Valid @RequestBody SellerProfileDto profileDto, @PathVariable("user_id") Long user_id){
        return sellerService.updateSeller(profileDto,user_id);
    }

    //update password


    @PatchMapping(path = "/updatePassword/{id}")
    public String passwordUpdate(@PathVariable(value = "id") Long id, @RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String confirmPassword, HttpServletResponse response) {
        if (passwordValidation.validPassword(oldPassword, newPassword, confirmPassword)) {
            return sellerService.passwordUpdate(id, oldPassword, newPassword, confirmPassword, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Password not matched!! or enter a valid password";
        }
    }


            //update address

    @PutMapping(path = "/updateAddress/{id}")
public String updateAddress(@PathVariable(value = "id") Long id,HttpServletResponse response)
    {
        return "password updated sucessfully!!";
    }

        }
