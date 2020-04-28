package security.oauth.bootloaders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import security.oauth.entities.*;
import security.oauth.repos.RolesRepository;
import security.oauth.repos.SellerRepository;
import security.oauth.repos.UserRepository;
import security.oauth.services.CurrentUserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


            //set grant_type=refresh_token,client_id=live-test,client_secret=abcde,refresh_token={token} when try to get new token using refresh_token
            //no username/password field


            Admin admin = new Admin();
            admin.setFirstName("Shivam");
            admin.setLastName("Sharma");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setActive(true);
            admin.setDeleted(false);
            admin.setLocked(false);
            admin.setExpired(false);
            //admin.set


            Set<Admin> adminSet=new HashSet<>();
            adminSet.add(admin);
            Roles roles = new Roles();
            roles.setAuthority("ROLE_ADMIN");
//
//            Roles roles1 = new Roles();
//            roles1.setAuthority("ROLE_CUSTOMER");
//
//            Roles roles2 = new Roles();
//            roles2.setAuthority("ROLE_SELLER");

            Set<Roles> roleSet = new HashSet<>();
            roleSet.add(roles);
//            roleSet.add(roles1);
//            roleSet.add(roles2);
            admin.setRoles(roleSet);

            userRepository.save(admin);

//copy paste give roles
            //Creating customer

        Customer customer=new Customer();
        customer.setFirstName("Vishal");
        customer.setLastName("Sharma");
        customer.setPassword(passwordEncoder.encode("customer"));
        customer.setEmail("customer@gmail.com");
        customer.setActive(true);
        customer.setDeleted(false);
        customer.setExpired(false);
        customer.setLocked(false);
        customer.setContact("9451023455");

        Set<Customer> customerSet=new HashSet<>();
        customerSet.add(customer);
        Roles roles1 = new Roles();
        roles1.setAuthority("ROLE_CUSTOMER");
        Set<Roles> roleSet1 = new HashSet<>();
        roleSet1.add(roles1);
        customer.setRoles(roleSet1);
        userRepository.save(customer);


        //Creating a seller

        Seller seller=new Seller();
        seller.setFirstName("Ankit");
        seller.setLastName("Sagar");
        seller.setPassword(passwordEncoder.encode("seller"));
        seller.setEmail("seller@gmail.com");
        seller.setActive(true);
        seller.setDeleted(false);
        seller.setExpired(false);
        seller.setLocked(false);
        seller.setCompnayContact("9524678234");

     ///Adding address to seller.
        Address address=new Address();
        address.setAddressLine("sector 143");
        address.setCity("Noida");
        address.setCountry("India");
        address.setDeleted(false);
        address.setLabel("Home");
        address.setState("Up");
        address.setZipCode("201309");
        Set<Address> sellerAddress=new HashSet<>();
        sellerAddress.add(address);
        //setting address to seller
        seller.setAddresses(sellerAddress);

        ///setting address to customer
        customer.setAddresses(sellerAddress);



        Set<Seller> sellerSet=new HashSet<>();

        sellerSet.add(seller);

        //Adding role to seller
        Roles roles2 = new Roles();
        roles2.setAuthority("ROLE_SELLER");

        Set<Roles> roleSet2 = new HashSet<>();
        roleSet2.add(roles2);

        customer.setRoles(roleSet2);
        userRepository.save(seller);
        userRepository.save(customer);

      //  Product product=new Product();

    }
    }

