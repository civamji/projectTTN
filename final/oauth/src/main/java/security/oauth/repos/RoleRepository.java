package security.oauth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import security.oauth.entities.Roles;
public interface RoleRepository extends JpaRepository<Roles,Long> {

}
