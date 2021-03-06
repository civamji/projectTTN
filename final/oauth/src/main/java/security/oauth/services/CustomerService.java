package security.oauth.services;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import security.oauth.dtos.AddressDto;
import security.oauth.dtos.CustomerProfileDto;
import security.oauth.dtos.CustomerRegistrationDto;
import security.oauth.entities.Address;
import security.oauth.entities.Customer;
import security.oauth.entities.User;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.AddressRepository;
import security.oauth.repos.CustomerRepository;
import security.oauth.repos.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailNotificationService emailNotificationService;


//show current user profile

    public CustomerProfileDto showCustomerProfile(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerProfileDto customerProfileDto = new CustomerProfileDto();
            BeanUtils.copyProperties(customer.get(), customerProfileDto);
            return customerProfileDto;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


    //show address

    public MappingJacksonValue showCustomerAddress(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerProfileDto customerDto = new CustomerProfileDto();
            BeanUtils.copyProperties(customer.get(), customerDto);
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("addresses");
               FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CustomerFilter", filter);

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customerDto);

            return mappingJacksonValue;
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }


    //show address
//    public AddressDto showAddress(String username){
//        Address address=new Address();
//
//        addressDto.setAddressLine(address.ge);
//        addressRepository.findByFirstName(username);
//        AddressDto addressDto=modelMapper.map(address,AddressDto.class);
//        System.out.println(" success!!");
//        return addressDto;
//    }

    //Update profile
    @Transactional
    @Modifying
    public String updateCustomer(CustomerProfileDto profileDto, Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        BeanUtils.copyProperties(profileDto, customer);
        if (customer.isPresent()) {
            customer.get().setFirstName(profileDto.getFirstName());
            customer.get().setLastName(profileDto.getLastName());
            customer.get().setContact(profileDto.getContact());
            customer.get().setEmail(profileDto.getEmail());
            customerRepository.save(customer.get());
            return "Profile updated successfully";
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }


    //Update password:Working--enter values in param
    @Transactional
    @Modifying
    public String updatePassword(Long id, String oldPass, String newPass, String confirmPass, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            if (passwordEncoder.matches(oldPass, user.get().getPassword())) {

                if (newPass.equals(confirmPass)) {
                    user.get().setPassword(passwordEncoder.encode(newPass));
                    userRepository.save(user.get());

                    String email = user.get().getEmail();
                    emailNotificationService.sendNotification("Password Changed", "Your password has changed", email);

                    return "Password successfully changed";
                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return "Success";
    }
//update address

    public String updateAddress(Long id, AddressDto addressDto, HttpServletRequest request) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new UsernameNotFoundException("no address fount with id " + id);
        }
        CustomerProfileDto customerDto = new CustomerProfileDto();
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());
        Set<Address> addresses = customer.getAddresses();
        addresses.forEach(a -> {
            if (a.getId() == address.get().getId()) {
                a.setCity(addressDto.getCity());
                a.setCountry(addressDto.getCountry());
                a.setLabel(addressDto.getLabel());
                a.setState(addressDto.getState());
                a.setZipCode(addressDto.getZipCode());
            }
        });
        customerRepository.save(customer);
        return "Success";
    }

    //delete address
    @Transactional
    public String deleteAddress(Long id, HttpServletRequest request) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new UsernameNotFoundException("no address fount with id " + id);
        }
        addressRepository.deleteById(id);
        return "Success";
    }

    public String validateCustomer(CustomerRegistrationDto customerDto) {
        StringBuilder sb = new StringBuilder();
        User user = userRepository.findByEmail(customerDto.getEmail());
        if (null != user) {
            sb.append("Email already exist");
        } else if (!customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            sb.append("Password not matched");
        } else {
            sb.append("validated");
        }
        return sb.toString();
    }


    public MappingJacksonValue getCustomerDetailsByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        CustomerRegistrationDto customerDto = new CustomerRegistrationDto();
        BeanUtils.copyProperties(customer, customerDto);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("password", "confirmPassword", "accountNonLocked", "roles");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("CustomerDto-Filter", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customerDto);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


    //add address
    public String addAddress(AddressDto addressDto, Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Address address = new Address();
            BeanUtils.copyProperties(addressDto, address);
            addressRepository.save(address);
            return "address added";
            } else {
            throw new UsernameNotFoundException("user not found");
        }

    }

    public AddressDto getAddress(String username){

       // Optional<Customer> customer=customerRepository.findByFirstName(username);
      //  Optional<Customer> customer=customerRepository.findByFirstName(username);
        AddressDto addressDto=new AddressDto();
        Customer customer=customerRepository.findByFirstName(username);
        Long id=customer.getId();
        Optional<Address> address=addressRepository.findById(id);
        BeanUtils.copyProperties(addressDto,address);
        return addressDto;


    }

    //Updating address
    public String addNewAddress(Principal principal, AddressDto newAddress) {
        String username=principal.getName();
      Customer  customer=customerRepository.findByFirstName(username);
      Long id=customer.getId();
      Optional<Address> address=addressRepository.findById(id);
      BeanUtils.copyProperties(address,newAddress);
      customerRepository.save(customer);
      return "sucessfull";
    }

//
//    public ResponseEntity<> getCustomerAddresses(String email) {
//  {
//            Customer customer = customerRepository.findByEmail(email);
//            BaseVO response;
//            Set<AddressDto> addressDtos = new HashSet<>();
//            Set<Address> addresses = customer.getAddresses();
//
//            addresses.forEach(
//                    (a)->addressDtos.add(addressService.toAddressDto(a))
//            );
//            response = new ResponseVO<Set>(addressDtos, null, new Date());
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//    }
}

