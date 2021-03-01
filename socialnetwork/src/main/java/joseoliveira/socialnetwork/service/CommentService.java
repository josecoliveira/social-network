package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.Comment;
import joseoliveira.socialnetwork.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        Iterable<Comment> it = commentRepository.findAll();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        it.forEach(comments::add);
        return comments;
    }

    public Comment findOneById(long id) {
        return commentRepository.findOneById(id);
    }

    public List<Comment> findAllByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
