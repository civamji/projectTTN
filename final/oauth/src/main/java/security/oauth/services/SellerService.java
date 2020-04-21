package security.oauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import security.oauth.custom_validators.PasswordValidation;
import security.oauth.dtos.SellerAddressDto;
import security.oauth.dtos.SellerProfileDto;
import security.oauth.dtos.SellerRegistrationDto;
import security.oauth.entities.Address;
import security.oauth.entities.Seller;
import security.oauth.entities.User;
import security.oauth.events.EmailNotificationService;
import security.oauth.events.UserEmailToken;
import security.oauth.repos.AddressRepository;
import security.oauth.repos.SellerRepository;
import security.oauth.repos.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class SellerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserEmailToken userEmailToken;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasswordValidation passwordValidation;



    @Autowired
    ModelMapper modelMapper;

    //view profile

    public SellerProfileDto getSellerDetials(Long userid){
        Optional<Seller> seller=sellerRepository.findById(userid);
        if(seller.isPresent()){
            SellerProfileDto sellerProfileDto=new SellerProfileDto();
            BeanUtils.copyProperties(seller.get(),sellerProfileDto);
            return sellerProfileDto;
        }
        else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }




    //Update profile

    @Transactional
    @Modifying
    public String updateSeller(SellerProfileDto sellerProfileDto, Long userId){
        Optional<Seller> seller = sellerRepository.findById(userId);
        if(seller.isPresent()) {
            seller.get().setFirstName(sellerProfileDto.getFirstName());
            seller.get().setLastName(sellerProfileDto.getLastName());
            seller.get().setCompnayContact(sellerProfileDto.getCompanyContact());
            seller.get().setgst(sellerProfileDto.getGst());
            
            //seller.get().setId(sellerProfileDto.getId());

            sellerRepository.save(seller.get());
            return "Profile update Successfully";
        }else {
            throw new UsernameNotFoundException("User not Found");
        }
    }


    //Update password

    @Transactional
    @Modifying

    public String passwordUpdate(@PathVariable(value = "id") Long id, @RequestParam String oldpassword, @RequestParam String newPassword, @RequestParam String confirmPassword, HttpServletResponse httpServletResponse){
       //find speciafic user
        Optional<User> user=userRepository.findById(id);
        //if user exists then other wise throw exception
        if(user.isPresent()) {
            //give password is matches with users real password or not
            if (passwordEncoder.matches(oldpassword, user.get().getPassword())) {
                //new password and confirm password matching
                if (newPassword.equals(confirmPassword)) {
                    //setting encoded new password to users password field
                    user.get().setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user.get());

                    //sending registered user a mail to notify him about password change.
                    String email = user.get().getEmail();
                    emailNotificationService.sendNotification("Attention!! ", "Password changed successfully", email);

                    return "Password changed successfully";
                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        else {
            throw new UsernameNotFoundException("User not found!!---->Enter right password!!");

            }
            return "Success!!";
        }




    public String validateSeller(SellerRegistrationDto sellerDto) {

        Seller seller = modelMapper.map(sellerDto, Seller.class);
//Check that confirm password is same as actual password

        if (sellerDto.getConfirmPassword() == seller.getPassword()) {
            userRepository.save(seller);
        } else
            System.out.println("Password not matched!!");

        return "Done";
    }

    //Update address


    public String updateAddress(Long id, SellerAddressDto addressDto, HttpServletRequest request) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw  new UsernameNotFoundException("no address fount with id " + id);
        }
        Seller seller = sellerRepository.findByEmail(userEmailToken.getUserEmail(request));
        Set<Address> addresses = seller.getAddresses();
        addresses.forEach(a->{
            if (a.getId() == address.get().getId()) {
                a.setAddressLine(addressDto.getAddressLine());
                a.setCity(addressDto.getCity());
                a.setCountry(addressDto.getCountry());
                a.setState(addressDto.getState());
                a.setZipCode(addressDto.getZipCode());
                a.setAddressLine(addressDto.getAddressLine());
            }
        });
        sellerRepository.save(seller);
        return "Success";
    }





    }

