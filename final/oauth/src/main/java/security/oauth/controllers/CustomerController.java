package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.entities.Customer;
import security.oauth.repos.CustomerRepository;
import security.oauth.services.CurrentUserService;
import security.oauth.services.CustomerService;
import security.oauth.services.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;




    //To show customerProfile
    @GetMapping("/customer/profile")
    public  CustomerProfileDto viewprofile(@RequestBody CustomerProfileDto customerProfileDto, HttpServletRequest request){

        String username=currentUserService.getUser();
        customerProfileDto=customerService.showProfile();
        return customerProfileDto;

        Customer customer=customerRepository.findByUsername(username);
//        return customerService.toCustomerViewProfileDto(customer);

    }

    //show address

    @GetMapping("/address/{Emailid}")
    public void showAddress(@PathVariable String Emailid)

    //To update customer profile

    @PutMapping("/update/profile/{Emailid}")
    public void updateProfile(@RequestBody CustomerProfileDto customerProfileDto,@PathVariable String Emailid){
        userService.updateProfile(customerDto);
    }


//Update password

    @PutMapping("/updateAddress/profile/{Emailid}")
    public String updateAddress(@RequestBody CustomerProfileDto customerProfileDto,@PathVariable String Emailid){
        userService.updatePassword(customerDto);
    }



    //customer profile by Email
    @GetMapping("/profile/{Emailid}")
    public MappingJacksonValue getCustomerProfile(@PathVariable("email") String email){
        return customerService.getCustomerDetailsByEmail(email);
    }


}
