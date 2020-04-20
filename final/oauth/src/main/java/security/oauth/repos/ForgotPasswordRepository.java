package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.ForgotPasswordToken;

public interface ForgotPasswordRepository extends CrudRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findByUserEmail(String email);
    void deleteByUserEmail(String email);

}
