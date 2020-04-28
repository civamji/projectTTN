package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.dtos.SellerProfileDto;
import security.oauth.dtos.SellerRegistrationDto;
import security.oauth.entities.Admin;
import security.oauth.entities.User;
import security.oauth.security.AppUserDetailsService;
import security.oauth.services.CustomerActivateService;
import security.oauth.services.CustomerService;
import security.oauth.services.EmailService;
import security.oauth.services.SellerService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@RestController
@RequestMapping(path = "/register")
public class RegistrationController {

    @Autowired
    private TokenStore tokenStore;

//    @Autowired
//    private SellerRepository sellerRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    //customer activate service
    @Autowired
    private CustomerActivateService customerActivateService;

    //Email service
    @Autowired
    EmailService emailService;


    //seller service
    @Autowired
    private SellerService sellerService;

    @GetMapping(path = "/hello")
    public String printingHello() {
        return "Welcome";
    }


    //registerCustomer

    //Customer can't register two time with same emailid
    @PostMapping(path = "/customer")
    public String registerCustomer(@Valid @RequestBody CustomerRegistrationDto customerDto, HttpServletResponse httpServletResponse){
        String message=appUserDetailsService.registerCustomer(customerDto);
        System.out.println(message + "Customer");
        // content equals
        if ("Registered Successfully".equals(message)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return message;
    }


    //Register seller
    @PostMapping(path = "/seller")
    public String registerSeller(@Valid @RequestBody SellerRegistrationDto sellerDto, HttpServletResponse httpServletResponse) {
        String message = appUserDetailsService.registerSeller(sellerDto);
        System.out.println(message + "Seller");
        if ("Registered Successfully".equals(message)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return message;
    }

//    @PostMapping(path = "/seller")
//    public Object registerSeller(@Valid @RequestBody SellerRegistrationDto sellerDto, HttpServletResponse response){
//        if(sellerService.validateSeller(sellerDto).equals("validated")) {
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            return appUserDetailsService.registerSeller(sellerDto);
//        }else {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return sellerService.validateSeller(sellerDto);
//        }
//    }


    //Confirm Customer Account


    //invalid token
    @PutMapping(path = "/confirm-account")
    public String confirmCustomerAccount(@RequestParam("token") String token, HttpServletResponse response) {
        String message = customerActivateService.activateCustomer(token);
        if (!message.equals("Success")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return message;
    }

    //confirm




    //Resend link
        //Working
    @PostMapping(path = "/resend-activation")
    public String resendLink(@RequestParam("email") String email, HttpServletResponse response) {
        String message = customerActivateService.resendLink(email);
        if (!message.equals("Success")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return message;
    }
}

