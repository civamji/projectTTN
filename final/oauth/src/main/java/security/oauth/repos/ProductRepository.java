package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
