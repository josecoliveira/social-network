package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post findOneById(long id);

    List<Post> findAllByUserId(long userId);
}
