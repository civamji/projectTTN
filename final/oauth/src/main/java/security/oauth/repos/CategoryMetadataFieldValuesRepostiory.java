package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.CategoryMetadataFieldValues;

public interface CategoryMetadataFieldValuesRepostiory extends CrudRepository<CategoryMetadataFieldValues,Long> {
}
