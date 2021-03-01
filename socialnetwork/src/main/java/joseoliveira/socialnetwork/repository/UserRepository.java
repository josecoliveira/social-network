package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
