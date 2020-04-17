package security.oauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.oauth.dtos.SellerProfileDto;
import security.oauth.dtos.SellerRegistrationDto;
import security.oauth.entities.Address;
import security.oauth.entities.Seller;
import security.oauth.entities.User;
import security.oauth.events.EmailNotificationService;
import security.oauth.repos.AddressRepository;
import security.oauth.repos.SellerRepository;
import security.oauth.repos.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    ModelMapper modelMapper;

    //Get seller Details

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


    //Update seller

    @Transactional
    @Modifying
    public String updateSeller(SellerProfileDto sellerProfileDto, Long userId){
        Optional<Seller> seller = sellerRepository.findById(userId);
        if(seller.isPresent()) {
            seller.get().setFirstName(sellerProfileDto.getFirstName());
            seller.get().setLastName(sellerProfileDto.getLastName());
            seller.get().setCompnayContact(sellerProfileDto.getCompanyContact());
            seller.get().setgst(sellerProfileDto.getGst());

            sellerRepository.save(seller.get());
            return "Profile update Successfully";
        }else {
            throw new UsernameNotFoundException("User not Found");
        }
    }

    public String validateSeller(SellerRegistrationDto sellerDto) {
        //first method to add fileds

//        Seller seller=new Seller();
//        seller.getLastName(sellerDto.getLastName());
//

        //Second method to map

        Seller seller = modelMapper.map(sellerDto, Seller.class);
//Check that confirm password is same as actual password

        if (sellerDto.getConfirmPassword() == seller.getPassword()) {
            userRepository.save(seller);
        } else
            System.out.println("Password not matched!!");

        return "Done";
    }





//        StringBuilder sb = new StringBuilder();
//        System.out.println("Seller Dto is : "+sellerDto);
//        User user = userRepository.findByEmail(sellerDto.getEmail());
//
//        System.out.println("Seller is : "+user);
//
//        System.out.println("Gst number is : "+sellerDto.getgst());
//        Seller seller = sellerRepository.findByGst(sellerDto.getgst());
//        if (null!=user){
//            sb.append("Email already exist");
//        }else if(!sellerDto.getPassword().equals(sellerDto.getConfirmPassword())){
//            sb.append("Password not matched");
//        }else if(null!=seller){
//            System.out.println("Seller Gst : "+seller);
//            sb.append("Gst number exists");
//        }else {
//            sb.append("validated");
//        }

    }

