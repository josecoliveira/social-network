package joseoliveira.socialnetwork.service;

import joseoliveira.socialnetwork.model.User;
import joseoliveira.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public List<User> findAll() {
//        Iterable<User> it = userRepository.findAll();
//        ArrayList<User> users = new ArrayList<User>();
//        it.forEach(users::add);
//        return users;
//    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
