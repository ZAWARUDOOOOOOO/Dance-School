package school.danceSite.controller;


import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.danceSite.config.securityFilter.TokenRefresher;
import school.danceSite.dao.entity.User;
import school.danceSite.dao.entityService.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRoleService service;

    public UserController(UserRoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/users")
                        .toUriString()
        );
        return ResponseEntity.created(uri).body(service.saveUser(user));
    }

    @PostMapping("/add-role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        service.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        TokenRefresher refresher = new TokenRefresher(service);
        refresher.refreshToken(request, response);
    }

    @Data
    static class RoleToUserForm {
        private String username;
        private String rolename;
    }
}
