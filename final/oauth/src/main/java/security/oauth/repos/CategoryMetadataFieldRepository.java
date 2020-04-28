package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.CategoryMetadataField;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField,Long> {
    CategoryMetadataField findByName(String firstName);
//    List<CategoryMetadataField> findAll();
//    List<CategoryMetadataField> findAll(Pageable pageable);

}
