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

            Admin admin = new Admin();
            admin.setFirstName("Shivam");
            admin.setLastName("Sharma");
            admin.setEmail("sharmashivam637@gmail.com");
            admin.setPassword(passwordEncoder.encode("pass"));
            admin.setActive(true);
            admin.setDeleted(false);
            admin.setLocked(false);
            admin.setExpired(false);

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

        Set<Seller> sellerSet=new HashSet<>();
        sellerSet.add(seller);
        Roles roles2 = new Roles();
        roles2.setAuthority("ROLE_SELLER");
        Set<Roles> roleSet2 = new HashSet<>();
        roleSet2.add(roles2);
        customer.setRoles(roleSet2);
        userRepository.save(seller);



    }
    }

