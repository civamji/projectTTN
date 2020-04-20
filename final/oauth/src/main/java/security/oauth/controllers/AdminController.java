package security.oauth.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.entities.User;
import security.oauth.repos.UserRepository;
import security.oauth.services.AdminService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "admin/home")
public class AdminController {

        @Autowired
        private AdminService adminService;

        @Autowired
        private UserRepository userRepository;


        @GetMapping(path = "/print")
        public String printingHello() {
            return "Welcome";
        }

//List<Dto> getCustomer()
//{
//    userrepo.findall();
//
//}

//        @GetMapping(path = "/customer")
//        public List<CustomerProfileDto> getCustomer(){
//            Iterable<User> user=userRepository.findAll();
//            return
//        }




        @PatchMapping(path = "/activateCustomer/{id}")
        public String activateCustomer(@PathVariable(value = "id") Long id, HttpServletResponse response){

            String message = adminService.activateCustomer(id,response);

            if(!message.equals("Account activated")){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            return message;
        }

        @PatchMapping(path = "/de-activateCustomer/{id}")
        public String deactivateCustomer(@PathVariable(value = "id") Long id,HttpServletResponse response){
            String message = adminService.deactivateCustomer(id,response);
            if(!message.equals("Account de-activated")){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            return message;
        }

        @PatchMapping(path = "/activateSeller/{id}")
        public String activateSeller(@PathVariable(value = "id") Long id, HttpServletResponse response){
            String message = adminService.activateSeller(id,response);
            if(!message.equals("Account activated")){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            return message;
        }

        @PatchMapping(path = "/de-activateSeller/{id}")
        public String deactivateSeller(@PathVariable(value = "id") Long id, HttpServletResponse response){
            String message = adminService.deactivateSeller(id,response);
            if(!message.equals("Account de-activated")){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            return message;
        }
    }
