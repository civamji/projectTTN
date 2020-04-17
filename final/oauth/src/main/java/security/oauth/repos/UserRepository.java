package security.oauth.repos;

import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmail(String email);
}
