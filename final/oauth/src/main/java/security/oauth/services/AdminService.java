package security.oauth.services;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import security.oauth.entities.Admin;
import security.oauth.entities.Customer;
import security.oauth.entities.Seller;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.AdminRepository;
import security.oauth.repos.CustomerRepository;
import security.oauth.repos.SellerRepository;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
@Autowired
    private CustomerRepository customerRepository;

@Autowired
    private SellerRepository sellerRepository;

@Autowired
    private AdminRepository adminRepository;

@Autowired
    private EmailNotificationService emailNotificationService;

@Autowired
    private CustomerActivateService customerActivateService;

//to do partial update use patch

    @PatchMapping("admin/activate/customer/{id}")
    public String activateCustomer(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<Admin> user = adminRepository.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (!user.get().isActive()) {
            user.get().setActive(true);
            adminRepository.save(user.get());
            // trigger mail
            emailNotificationService.sendNotification("ACTIVATED", "BINGOO YOUR ACCOUNT HAS BEEN ACTIVATED", user.get().getEmail());
            return "Success";
        }
        adminRepository.save(user.get());
        System.out.println("already activated");
        return "Success";
    }

    @PatchMapping("admin/deactivate/customer/{id}")
    public String deactivateCustomer(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<Admin> user = adminRepository.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (user.get().isActive()) {
            user.get().setActive(false);
            adminRepository.save(user.get());
            // trigger mail
            emailNotificationService.sendNotification("DEACTIVATED", "OHH!! YOUR ACCOUNT HAS BEEN DEACTIVATED", user.get().getEmail());
            return "Success";
        }
        adminRepository.save(user.get());
        System.out.println("already deactivated");
        return "Success";
    }

    @PatchMapping("admin/activate/seller/{id}")
    public String activateSeller(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<Admin> user = adminRepository.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (!user.get().isActive()) {
            user.get().setActive(true);
            adminRepository.save(user.get());
            // trigger mail
            emailNotificationService.sendNotification("ACTIVATED", "BINGOO!! YOUR ACCOUNT HAS BEEN ACTIVATED", user.get().getEmail());
            return "Success";
        }
        adminRepository.save(user.get());
        System.out.println("already activated");
        return "Success";
    }

    @PatchMapping("admin/deactivate/seller/{id}")
    public String deactivateSeller(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<Admin> user = adminRepository.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (user.get().isActive()) {
            user.get().setActive(false);
            adminRepository.save(user.get());
            // trigger mail
            emailNotificationService.sendNotification("DEACTIVATED", "YOUR ACCOUNT HAS BEEN DEACTIVATED", user.get().getEmail());
            return "Success";
        }
        adminRepository.save(user.get());
        System.out.println("already deactivated");
        return "Success";
    }

//Uncomment this

    //ALERT EXPLICITLY CASTED TO PAGEABLE


    public MappingJacksonValue registeredCustomers(String page, String size, String SortBy){
        List<Customer> customers = customerRepository.findAll(PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.by(SortBy))).getContent();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName","lastName","email","active");
        FilterProvider filterProvider =  new SimpleFilterProvider().addFilter("CustomerFilter",filter);

        MappingJacksonValue message = new MappingJacksonValue(customers);

        message.setFilters(filterProvider);
        return message;
    }


    //ALERT EXPLICITLY CASTED TO PAGEABLE

    public MappingJacksonValue registeredSellers(String page,String size, String SortBy){
        List<Seller> sellers = sellerRepository.findAll( PageRequest.of(Integer.parseInt(page),Integer.parseInt(size), Sort.by(SortBy))).getContent();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName","lastName","email","active","companyName","companyContact","addresses");
        FilterProvider filterProvider =  new SimpleFilterProvider().addFilter("Seller-Filter",filter);

        MappingJacksonValue message = new MappingJacksonValue(sellers);

        message.setFilters(filterProvider);
        return message;
    }

}
