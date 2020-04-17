package security.oauth.services;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.entities.Customer;
import security.oauth.entities.User;
import security.oauth.repos.CustomerRepository;
import security.oauth.repos.UserRepository;

@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;


//
//    public CustomerProfileDto convtToCustomerDto(Customer customer){
//        CustomerProfileDto customerProfileDto= ModelMapper.map(customer,CustomerProfileDto.class);
//        System.out.println("success");
//        return customerProfileDto;
//    }
//
//    public CustomerProfileDto toCustomerViewProfileDto(Customer customer){
//
//    }













    public String validateCustomer(CustomerRegistrationDto customerDto) {
        StringBuilder sb = new StringBuilder();
        User user = userRepository.findByEmail(customerDto.getEmail());
        if (null!=user){
            sb.append("Email already exist");
        }else if(!customerDto.getPassword().equals(customerDto.getConfirmPassword())){
            sb.append("Password not matched");
        }else {
            sb.append("validated");
        }
        return sb.toString();
    }

    public MappingJacksonValue getCustomerDetails(String email){
        Customer customer = customerRepository.findByEmail(email);
        CustomerRegistrationDto customerDto = new CustomerRegistrationDto();
        BeanUtils.copyProperties(customer,customerDto);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("password","confirmPassword","accountNonLocked","roles");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CustomerDto-Filter",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customerDto);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

}

