//package security.oauth.services;
//
//package Security.Spring_security;
//import  Security.Spring_security.Entiity.Role;
//import Security.Spring_security.Entiity.User;
//import Security.Spring_security.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class Bootstrap implements ApplicationRunner
//{
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception
//    {
//        Set<User> users = new HashSet<>();
//        Set<Role> roles = new HashSet<>();
//
//        Role role=new Role();
//        role.setRole("ROLE_ADMIN");
//        User user = new User();
//        user.setUsername("Shivam");
//        user.setPassword(new BCryptPasswordEncoder().encode("civam"));
//        users.add(user);
//        role.setUsers(users);
//        roles.add(role);
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        Set<User> users1 = new HashSet<>();
//        Set<Role> roles1 = new HashSet<>();
//        Role role1 = new Role();
//        role1.setRole("ROLE_USER");
//        User user1 = new User();
//        user1.setUsername("himanshu");
//        user1.setPassword(new BCryptPasswordEncoder().encode("himanshu"));
//        users.add(user1);
//        role.setUsers(users1);
//        roles1.add(role1);
//        user1.setRoles(roles1);
//        userRepository.save(user1);
//
//        Set<User> users2 = new HashSet<>();
//        Set<Role> roles2 = new HashSet<>();
//        Role role2 = new Role();
//        role2.setRole("ROLE_ADMIN");
//        Role role3 = new Role();
//        role3.setRole("ROLE_USER");
//        User user2 = new User();
//        user2.setUsername("ankit");
//        user2.setPassword(new BCryptPasswordEncoder().encode("ankit"));
//        users.add(user2);
//        role.setUsers(users2);
//        roles2.add(role2);
//        roles2.add(role3);
//        user2.setRoles(roles2);
//        userRepository.save(user2);
//
//
//    }
//}