package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.exceptions.ValidationException;
import joseoliveira.socialnetwork.model.Album;
import joseoliveira.socialnetwork.model.Post;
import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.service.AlbumService;
import joseoliveira.socialnetwork.service.PostService;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private AlbumService albumService;

    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (userService.existsByUsername(username)) {
            throw new ValidationException("Username already existed");
        }
        String name = body.get("name");
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String email = body.get("email");
        userService.save(new User(name, username, encodedPassword, email));
        return true;
    }

    @GetMapping("/user/{userId}/post")
    public List<Post> findPostsByUserId(@PathVariable long userId) {
        if (!userService.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
        return postService.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/album")
    public List<Album> findAlbumsByUserId(@PathVariable long userId) {
        if (!userService.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found"
            );
        }
        return albumService.findByUserId(userId);
    }

}
