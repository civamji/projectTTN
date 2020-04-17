package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Address;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
