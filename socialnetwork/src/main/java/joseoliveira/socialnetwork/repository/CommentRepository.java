package joseoliveira.socialnetwork.repository;

import joseoliveira.socialnetwork.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findOneById(long id);

    List<Comment> findAllByPostId(long postId);
}
