package security.oauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.oauth.dtos.SellerRegistrationDto;
import security.oauth.entities.Seller;
import security.oauth.entities.User;
import security.oauth.repos.SellerRepository;
import security.oauth.repos.UserRepository;

@Service
public class SellerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    ModelMapper modelMapper;

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
        return sb.toString();
    }
}
