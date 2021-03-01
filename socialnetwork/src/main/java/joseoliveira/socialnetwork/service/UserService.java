package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        Iterable<User> it = userRepository.findAll();
        ArrayList<User> users = new ArrayList<User>();
        it.forEach(users::add);
        return users;
    }

    public Long count() {
        return userRepository.count();
    }

    public Optional<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
}
