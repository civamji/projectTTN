package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.ProductVariation;

public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {
}
