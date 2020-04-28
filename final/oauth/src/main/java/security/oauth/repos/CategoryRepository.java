package security.oauth.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import security.oauth.entities.Category;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findByName(String categoryName);
    @Query(value = "select * from category where parent_id is null", nativeQuery = true)
    List<Category> findByParentIdIsNull();

    List<Category> findAll();
   List<Category> findAll(Pageable pageable);

    @Query(value = "delete from category_metadata_field_values where category_id = :Id", nativeQuery = true)
    void deleteCategoryById(@Param("Id") Long Id);

}
