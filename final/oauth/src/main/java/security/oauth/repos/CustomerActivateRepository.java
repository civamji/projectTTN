package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.ActivateCustomer;

public interface CustomerActivateRepository extends CrudRepository<ActivateCustomer,Long> {

    ActivateCustomer findByUserEmail(String email);
    ActivateCustomer findByToken(String token);
    void deleteByUserEmail(String email);
}
