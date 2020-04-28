package security.oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.oauth.custom_validators.EmailValidator;
import security.oauth.entities.ActivateCustomer;
import security.oauth.entities.User;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.CustomerActivateRepository;
import security.oauth.repos.UserRepository;

import java.util.UUID;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CustomerActivateService {
    @Autowired
    EmailValidator email;

    @Autowired
    CustomerActivateRepository customerActivateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailNotificationService emailNotificationService;

    @Transactional
    public String activateCustomer(String token) {
        ActivateCustomer activateCustomer = customerActivateRepository.findByToken(token);
        System.out.println("customer is"+activateCustomer);
        try {
            if (activateCustomer.getToken().equals(null)) {
                System.out.println("success");
            }

        } catch (NullPointerException ex) {
            return "invalid token";
        }
        Date date = new Date();
        long diff = date.getTime() - activateCustomer.getExpiryDate().getTime();
        long diffHours = diff / (60 * 60 * 1000);
        // if token expire
        if (diffHours > 24) {
         //   customerActivateRepository.deleteByUserEmail(activateCustomer.getUserEmail());

            String newToken = UUID.randomUUID().toString();

            ActivateCustomer localCustomerActivate = new ActivateCustomer();
            localCustomerActivate.setToken(newToken);
            localCustomerActivate.setUserEmail(activateCustomer.getUserEmail());
            localCustomerActivate.setExpiryDate(new Date());

            customerActivateRepository.save(localCustomerActivate);

            emailNotificationService.sendNotification("RE-ACCOUNT ACTIVATE TOKEN", "http://localhost:8080/register/confirm-account?token=" + newToken, activateCustomer.getUserEmail());

            return "Token has expired";
        }
        if (activateCustomer.getToken().equals(token)) {
            User user = userRepository.findByEmail(activateCustomer.getUserEmail());
            user.setActive(true);
            userRepository.save(user);
            emailNotificationService.sendNotification("ACCOUNT ACTIVATED", "Your account has been activated", activateCustomer.getUserEmail());
           // customerActivateRepository.deleteByUserEmail(activateCustomer.getUserEmail());
            return "Success";
        }

        return "Success";
    }

    @Transactional
    public String resendLink(String email) {

        User user = userRepository.findByEmail(email);
        try {
            if (user.getEmail().equals(null)) {
            }
        } catch (NullPointerException ex) {
            return "no email found";
        }
        if (user.isActive()) {
            return "Account already active";
        }
        if (!user.isActive()) {
       //     customerActivateRepository.deleteByUserEmail(email);

            String newToken = UUID.randomUUID().toString();

            ActivateCustomer localCustomerActivate = new ActivateCustomer();
            localCustomerActivate.setToken(newToken);
            localCustomerActivate.setUserEmail(email);
            localCustomerActivate.setExpiryDate(new Date());

            customerActivateRepository.save(localCustomerActivate);

            emailNotificationService.sendNotification("RE-ACCOUNT ACTIVATE TOKEN", "http://localhost:8080/register/confirm-account?token=" + newToken, email);

            return "Success";
        }

        return "Success";
    }
}