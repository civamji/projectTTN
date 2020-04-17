package security.oauth.bootloaders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import security.oauth.entities.Admin;
import security.oauth.entities.Roles;
import security.oauth.entities.Seller;
import security.oauth.repos.RoleRepository;
import security.oauth.repos.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.count()<1){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Admin admin = new Admin();
            admin.setFirstName("Shivam");
            admin.setLastName("Sharma");
            admin.setEmail("sharmashivam637@gmail.com");
            admin.setPassword(passwordEncoder.encode("pass"));
            admin.setActive(true);
            admin.setDeleted(false);

            Roles role = new Roles();
            role.setAuthority("ROLE_ADMIN");
            Roles role1 = new Roles();
            role1.setAuthority("ROLE_CUSTOMER");
            Roles role2 = new Roles();
            role1.setAuthority("ROLE_SELLER");
            Set<Roles> roleSet = new HashSet<>();
            roleSet.add(role);
            roleSet.add(role1);
            roleSet.add(role2);
            admin.setRoles(roleSet);



            userRepository.save(admin);
            System.out.println("Total users saved::"+userRepository.count());
        }
    }
}
