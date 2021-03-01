package joseoliveira.socialnetwork.controller;

import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/users")
//    public List<User> allUsers() {
//        return userService.findAll();
//    }

    @GetMapping("/users/count")
    public Long count() {
        return userService.count();
    }

    @GetMapping("/users")
    public Optional<User> getUserByUsername(@RequestParam String username) {
        return userService.findOneByUsername(username);
    }

}
