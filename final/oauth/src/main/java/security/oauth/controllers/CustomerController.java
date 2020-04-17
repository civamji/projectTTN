package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.entities.Customer;
import security.oauth.repos.CustomerRepository;
import security.oauth.services.CurrentUserService;
import security.oauth.services.CustomerService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerProfileDto customerDto;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private CustomerRepository customerRepository;

//    @GetMapping("/profile")
//
//    CustomerProfileDto viewProfile(){
//    String username=currentUserService.getUser();
//        Customer customer=customerRepository.findByUsername(username);
//        return customerService
//    }




//    private CustomerProfileDto getCustomerProfile(String email, HttpServletResponse httpServletResponse)
//    {
//
//    }




//used to send custom 404 responce page
//    @RequestMapping("*")
//    @ResponseBody
//    public String fallbackMethod(){
//        return "fallback method";
//    }

 //   @GetMapping("/customer/home")


}
