package security.oauth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Roles;
public interface RolesRepository extends CrudRepository<Roles,Long> {

}
