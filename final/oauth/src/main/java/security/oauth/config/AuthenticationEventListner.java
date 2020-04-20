package security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import security.oauth.entities.User;
import security.oauth.entities.UserLoginAttempts;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.UserAttemptsReposiitory;
import security.oauth.repos.UserRepository;

import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class AuthenticationEventListner {
@Autowired
UserAttemptsReposiitory userAttemptsRepo;
@Autowired
    UserRepository userRepository;
@Autowired
    EmailNotificationService emailNotificationService;
@EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

    int counter;



    // get current looged in user
//    Authentication authentication= (Authentication) new SecurityContextHolder().getContext();
//    String currentprincipal=authentication.getName();

    String userEmail = (String) event.getAuthentication().getPrincipal();
    Optional<UserLoginAttempts> userLoginFailCounter = userAttemptsRepo.findByEmail(userEmail);

    if (!userLoginFailCounter.isPresent()) {
        UserLoginAttempts userLoginFailCounter1 = new UserLoginAttempts();
        userLoginFailCounter1.setAttempts(1);
        userLoginFailCounter1.setEmail(userEmail);
        userAttemptsRepo.save(userLoginFailCounter1);
    }
    if (userLoginFailCounter.isPresent()) {
        counter = userLoginFailCounter.get().getAttempts();
        System.out.println(counter);
        if (counter>=2) {
            User user = userRepository.findByEmail(userEmail);
            user.setLocked(true);
            emailNotificationService.sendNotification("ACCOUNT LOCKED","YOUR ACCOUNT HAS BEEN LOCKED",userEmail);
            userRepository.save(user);
        }
        UserLoginAttempts userLoginFailCounter1 = userLoginFailCounter.get();
        userLoginFailCounter1.setAttempts(++counter);
        userLoginFailCounter1.setEmail(userEmail);
        System.out.println(userLoginFailCounter1+"-----------------");
        userAttemptsRepo.save(userLoginFailCounter1);
    }

}


    @EventListener
    public void authenticationPass(AuthenticationSuccessEvent event) {
        LinkedHashMap<String,String> userMap = new LinkedHashMap<>();
        try {
            userMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
        } catch (ClassCastException ex) {

        }
        String userEmail = new String();
        try {
            userEmail = userMap.get("username");
        } catch (NullPointerException e) {
        }
        Optional<UserLoginAttempts> userLoginFailCounter = userAttemptsRepo.findByEmail(userEmail);
        if (userLoginFailCounter.isPresent()){
            userAttemptsRepo.deleteById(userLoginFailCounter.get().getId());
        }
    }
}

