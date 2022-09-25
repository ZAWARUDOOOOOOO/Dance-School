package school.danceSite.dao.entityservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import school.danceSite.dao.entity.User;
import school.danceSite.dao.entityrepository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(User user){
        User existingUser = userRepository.findByUsername(user.getUsername());
        return existingUser.equals(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
