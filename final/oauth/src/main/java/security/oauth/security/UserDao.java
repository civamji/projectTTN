package security.oauth.security;

import com.nimbusds.jose.util.JSONObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.oauth.entities.User;
import security.oauth.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {

    @Autowired
    UserRepository userRepository;

    //LoadUser by Email


    public AppUser loadUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        if (email != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                SimpleGrantedAuthority grantAuthority = new SimpleGrantedAuthority(role.getAuthority());
                authorities.add(grantAuthority);
                System.out.println("authority"+authorities);
            });
            return new AppUser(user.getFirstName(), user.getPassword(), authorities,user.isActive(),!user.isLocked(),!user.isExpired());
        } else {
            throw new RuntimeException();
        }
    }

}