package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.Post;
import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.service.JwtUserDetailsService;
import joseoliveira.socialnetwork.service.PostService;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private JwtUserDetailsService jwtUserDetailsService;

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post findOneId(@PathVariable long id) {
        Post post = postService.findOneById(id);
        if (post == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post not found"
            );
        }
        return postService.findOneById(id);
    }

    @GetMapping("/posts/userId/{userId}")
    public List<Post> findAllUserId(@PathVariable long userId) {
        return postService.findAllUserId(userId);
    }

    @PostMapping("/posts")
    public Boolean save(@RequestBody Map<String, String> body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userService.findByUsername(userDetails.getUsername()).getId();
        System.out.println(userId);
        String title = body.get("title");
        String postBody = body.get("body");
        postService.save(new Post(userId, title, postBody));
        return true;
    }

}
