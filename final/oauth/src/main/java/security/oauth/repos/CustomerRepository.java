package security.oauth.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

    //native query
    Customer findByEmail(String email);
    Page<Customer> findAll(Pageable pageable);
    Customer findByUsername(String username);

}
