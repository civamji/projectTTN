package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
