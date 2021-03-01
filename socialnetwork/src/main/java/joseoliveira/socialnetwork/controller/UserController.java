package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.exceptions.ValidationException;
import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (userService.existsByUsername(username)){
            throw new ValidationException("Username already existed");
        }
        String name = body.get("name");
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String email = body.get("email");
        userService.save(new User(name, username, encodedPassword, email));
        return true;
    }

}
