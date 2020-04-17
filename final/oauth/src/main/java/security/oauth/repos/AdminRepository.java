package security.oauth.repos;

import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Admin;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin,Long> {
    Optional<Admin> findById(Long id);
}
