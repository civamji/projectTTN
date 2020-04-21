package security.oauth.bootloaders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import security.oauth.entities.Address;
import security.oauth.entities.Admin;
import security.oauth.entities.Roles;
import security.oauth.entities.Seller;
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
            admin.setFirstName("Vishal");
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


            //Adding seller

//            Set<Roles> rolesSet1=new HashSet<>();
//            rolesSet1.add(roles2);
//
//            Seller seller=new Seller();
//            seller.setComapnyName("samsung");
//            seller.setCompnayContact("8090604146");
//            seller.setgst("asdfghjkloiuytr");
//            seller.setFirstName("shivam");
//            seller.setLastName("sharma");
//            seller.setPassword("samsung@123");
//            seller.setEmail("civamofficial@gmail.com");
//            seller.setActive(true);
//            seller.setDeleted(false);
//
//            seller.addRole(new Roles("ROLE_SELLER"));
//
//            seller.setRoles(rolesSet1);
//
//
//
//          //  userRepository.save(seller);
//           // sellerRepository.save(seller);
//
//
//
//            Seller seller1 = new Seller("abc@tothenew.com", "seller", "", "seller", "bh7ht754r5", "amalgam pvt. lmt.", "9999988817");
//            seller1.setPassword(passwordEncoder.encode("pass"));
//            seller1.addAddress(new Address("A552", "UP", "Noida", "india", "229071", "office"));
//            seller1.setActive(true);
//            System.out.println("Total users saved::"+userRepository.count());
//            seller1.setRoles(rolesSet1);
//            userRepository.save(seller1);
//            userRepository.save(seller);

        }
    }

