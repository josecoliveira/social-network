package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.Comment;
import joseoliveira.socialnetwork.service.CommentService;
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
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment findOneById(@PathVariable long id) {
        Comment comment = commentService.findOneById(id);
        if (comment == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Comment not found"
            );
        }
        return comment;
    }

    @PostMapping("/comments")
    public Boolean save(@RequestBody Map<String, String> body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userService.findByUsername(userDetails.getUsername()).getId();
        long postId = Long.parseLong(body.get("postId"));
        String commentBody = body.get("body");
        commentService.save(new Comment(postId, userId, commentBody));
        return true;
    }

}
