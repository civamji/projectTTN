package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.User;
import security.oauth.entities.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}