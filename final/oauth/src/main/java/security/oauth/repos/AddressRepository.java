package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Address;
import security.oauth.entities.Customer;

public interface AddressRepository extends CrudRepository<Address,Long> {


    Customer findByFirstName(String username);
}
