package school.danceSite.dao.entityService;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.danceSite.dao.entity.Role;
import school.danceSite.dao.entity.User;
import school.danceSite.dao.entityRepository.RoleRepository;
import school.danceSite.dao.entityRepository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserRoleService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserRoleService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //добавить логику проверки на существование юзеров с такими никами
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
