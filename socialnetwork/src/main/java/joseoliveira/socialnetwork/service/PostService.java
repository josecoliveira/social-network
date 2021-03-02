package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.Post;
import joseoliveira.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        Iterable<Post> it = postRepository.findAll();
        ArrayList<Post> posts = new ArrayList<Post>();
        it.forEach(posts::add);
        return posts;
    }

    public Post findOneById(long id) {
        return postRepository.findOneById(id);
    }

    public List<Post> findAllByUserId(long userId) {
        return postRepository.findAllByUserId(userId);
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
