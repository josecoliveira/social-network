package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
}
