package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.UserLoginAttempts;

import java.util.Optional;

public interface UserAttemptsReposiitory extends CrudRepository<UserLoginAttempts,Long> {
    Optional<UserLoginAttempts> findByEmail(String email);

}
