package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.Comment;
import joseoliveira.socialnetwork.model.Post;
import joseoliveira.socialnetwork.service.CommentService;
import joseoliveira.socialnetwork.service.PostService;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/post", params = {})
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping(value = "/post", params = {"userId"})
    public List<Post> findAllUserId(@RequestParam long userId) {
        if (!userService.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
        return postService.findAllByUserId(userId);
    }

    @GetMapping("/post/{id}")
    public Post findOneId(@PathVariable long id) {
        Post post = postService.findOneById(id);
        if (post == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post not found"
            );
        }
        return post;
    }

    @GetMapping("/post/{postId}/comment")
    public List<Comment> findAllCommentsByPostId(@PathVariable long postId) {
        if (postService.findOneById(postId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post not found"
            );
        }
        return commentService.findAllByPostId(postId);
    }

    @PostMapping("/post")
    public Boolean save(@RequestBody Map<String, String> body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userService.findByUsername(userDetails.getUsername()).getId();
        String title = body.get("title");
        String postBody = body.get("body");
        postService.save(new Post(userId, title, postBody));
        return true;
    }

}
