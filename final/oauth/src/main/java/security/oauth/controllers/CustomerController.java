package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.custom_validators.PasswordValidation;
import security.oauth.dtos.AddressDto;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.repos.CustomerRepository;
import security.oauth.services.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(path = "customer/home")
public class CustomerController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerProfileDto customerDto;

//    @Autowired
//    private CurrentUserService currentUserService;

    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    private UserService userService;

    @Autowired
    private PasswordValidation passwordValidation;

//total: 5 URI

    //1.
    //customer profile:Working

    @GetMapping(path = "/getCustomerDetails/{id}")
    public CustomerProfileDto showCustomerProfile(@PathVariable("id") Long id){
        return customerService.showCustomerProfile(id);
    }

//2.Get customer address:Partially Working


    @GetMapping(path = "/getCustomerAddresses/{id}")
    public MappingJacksonValue getCustomerAddress(@PathVariable("id") Long id){
        return customerService.showCustomerAddress(id);
    }

//Getting address of current customer

    @GetMapping("/addresses")
    public AddressDto getCustomerAddresses(Principal principal){

        //Principal principal = request.getUserPrincipal();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        return currentPrincipalName;


//        String username = principal.getName();
//        return username;

        String username= principal.getName();

        return customerService.getAddress(username);

    }

    //Updating address
    @PostMapping("/addAddress")
    public String addNewAddress(Principal principal,@RequestBody AddressDto addressToAdd){
        return customerService.addNewAddress(principal,addressToAdd);
    }



    //3.
    //Update customer:Working well
    @PutMapping(path = "profile-update/{id}")
    public String updateProfile(@Valid @RequestBody CustomerProfileDto profileDto, @PathVariable("id") Long id){
        return customerService.updateCustomer(profileDto,id);
    }



    //Update Password:Working enter values in Param
    @PatchMapping("/updatePassword/{id}")
    public String passwordUpdate(@PathVariable(value = "id") Long id,
                                 @RequestParam String oldPass,@RequestParam String newPass,@RequestParam String confirmPass, HttpServletResponse response) {
        if (passwordValidation.validPassword(oldPass, newPass, confirmPass)) {
            return customerService.updatePassword(id, oldPass, newPass, confirmPass, response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Password must be matched or password must be of minimum 8 characters and maximum 15 characters and must contain 1 uppercase letter,1 lowercase letter,1 digit and 1 special character";
        }
    }


///Delete address
@DeleteMapping("/delete-address/{id}")
public String deleteAddress(@PathVariable Long id,HttpServletResponse response,HttpServletRequest request) {
    String getMessage = customerService.deleteAddress(id,request);
    if ("Success".contentEquals(getMessage)) {
        response.setStatus(HttpServletResponse.SC_CREATED);
    } else {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    return getMessage;
}


//Update address

    @PutMapping("/updateAddress/{id}")
    public String updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto, HttpServletResponse response, HttpServletRequest request) {
        String getMessage = customerService.updateAddress(id,addressDto,request);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    //add address

    @PutMapping("/add-address/{id}")
    public String addAddress(@Valid @RequestBody AddressDto addressDto,@PathVariable(value = "id") Long id){
        return customerService.addAddress(addressDto,id);
    }

    
}
