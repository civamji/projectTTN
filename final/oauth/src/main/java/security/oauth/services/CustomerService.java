package security.oauth.services;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import security.oauth.dtos.AddressDto;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.entities.Address;
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

    @Autowired
    private CustomerProfileDto customerProfileDto;

    @Autowired
    private AddressDto addressDto;

    @Autowired
    private ModelMapper modelMapper;
    private Customer customer;
    private Address address;


    public CustomerProfileDto showProfile(){
        CustomerProfileDto customerProfileDto= modelMapper.map(customer,CustomerProfileDto.class);
        System.out.println("success");
        return customerProfileDto;
    }

    //show address
    public AddressDto showAddress(String email){
        //user with email
        user object
        AddressDto addressDto=modelMapper.map(address,AddressDto.class);
        System.out.println("success");
        return addressDto;
    }


public String viewaddress(String email){
        Customer customer1=customerRepository.findByEmail(email);
        return customer1.getAddresses();
}

public CustomerProfileDto updateAddress(){
        Customer customer=customerRepository.findByEmail();
        customer.setAddresses();

}



    public CustomerProfileDto updateProfile(){

        Customer customer=customerRepository.findById();
        customerProfileDto.setFirstName(customer.getFirstName());
        customerProfileDto.setLastName(customer.getLastName());
        customerProfileDto.setId(customer.getId());
        customerProfileDto.setContact(customer.getContact());

        return customerProfileDto;
    }


    public CustomerProfileDto updatePassword(String email){
        Customer customer=customerRepository.findByEmail();
        customerProfileDto.

    }

    public CustomerProfileDto updateProfile(String email){


        return customerProfileDto;
    }

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


    public MappingJacksonValue getCustomerDetailsByEmail(String email){
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

