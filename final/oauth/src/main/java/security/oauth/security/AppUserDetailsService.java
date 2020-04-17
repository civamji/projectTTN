package security.oauth.security;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.dtos.SellerRegistrationDto;
import security.oauth.entities.*;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    CustomerActivateRepository customerActivateRepository;

    @Transactional
    public String registerCustomer(CustomerRegistrationDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        String pass = passwordEncoder.encode(customer.getPassword());

        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles("CUSTOMER"));
        customer.setPassword(pass);
        customer.setRoles(roles);

        customerRepository.save(customer);

        String token = UUID.randomUUID().toString();

        ActivateCustomer customerActivate = new ActivateCustomer();
        customerActivate.setToken(token);
        customerActivate.setUserEmail(customer.getEmail());
        customerActivate.setExpiryDate(new Date());

        customerActivateRepository.save(customerActivate);
        String  email = customer.getEmail();

        emailNotificationService.sendNotification("ACCOUNT ACTIVATE TOKEN","To confirm your account, please click here : "
                +"http://localhost:8080/register/confirm-account?token="+token,email);

        return "Registration Successful";
    }


    @Transactional
    public String  registerSeller(SellerRegistrationDto sellerDto){
        Seller seller = new Seller();
        BeanUtils.copyProperties(sellerDto, seller);

        if(seller.getAddresses().size() == 1) {
            String pass = passwordEncoder.encode(seller.getPassword());
            seller.setPassword(pass);
            sellerRepository.save(seller);
            return "Registration Successful";
        }else {
            return "Seller cannot have multiple addresses";
        }
    }

    @Transactional
    public User registerAdmin(Admin admin)
    {
        String pass = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(pass);
        return adminRepository.save(admin);
    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String encryptedPassword = passwordEncoder.encode("pass");
        System.out.println("Trying to authenticate user ::" + email);
        System.out.println("Encrypted Password ::"+encryptedPassword);

        //loadUserByName-->loadUserByEmail
        UserDetails userDetails = userDao.loadUserByEmail(email);


        return userDetails;
    }
}