package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.ProductReview;

public interface ProductReviewRepository extends CrudRepository<ProductReview,Long> {
}
