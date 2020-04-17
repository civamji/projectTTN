//package security.oauth.events;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequest;
//import security.oauth.entities.User;
//import security.oauth.services.UserService;
//
//import javax.validation.constraints.Null;
//import java.util.LinkedHashMap;
//
//
//@Component
//public class AuthenticationEventListener {
//
//    @Autowired
//    UserService userService;
//
//    @EventListener
//    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
//        int counter;
//        String userEmail = (String) event.getAuthentication().getPrincipal();
//
//        try{
//            User user = userService.getUserByEmail(userEmail);
//            userService.registerUnsuccessfulLogin(user);
//            userService.saveRegisteredUser(user);
//        }catch(NullPointerException ex){
//            System.out.println("##### - exception caught = " + ex);
//        }
//    }
//
//    @EventListener
//    public void authenticationPass(AuthenticationSuccessEvent event) {
//
//        if (event.getSource() instanceof UsernamePasswordAuthenticationToken) {
//
//            System.out.println("====================== event triggered =======================");
//
//            LinkedHashMap<String,String> userMap = new LinkedHashMap<>();
//            try {
//                System.out.println("getting details into the map");
//                userMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
//                System.out.println("got details into the map");
//                System.out.println(userMap);
//            } catch (ClassCastException ex) {
//                System.out.println("===================== exception caught = " + ex);
//            }
//
//            String userEmail = new String();
//
//            try {
//                System.out.println("====================== getting username =======================");
//                userEmail = userMap.get("username");
//                System.out.println(userEmail);
//                System.out.println("====================== got the username =======================");
//            } catch (NullPointerException ex) {
//                System.out.println("============ exception caught = " + ex);
//            }
//
//            User user = userService.getUserByEmail(userEmail);
//            System.out.println(" ##### user found");
//            System.out.println(user);
//            try{
//                System.out.println(user.getFirstName());
//                userService.registerSuccessfulLogin(user);
//                System.out.println("value set");
//                userService.saveRegisteredUser(user);
//                System.out.println("user saved successfully ");
//            }
//            catch(NullPointerException ex){
//                System.out.println("###### exception caught = " + ex);
//            }
//        }
//
//    }
//
//}
