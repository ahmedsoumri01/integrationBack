package CrudMysqlJwtSpringIntegration.admin;


import CrudMysqlJwtSpringIntegration.auth.AuthenticationService;
import CrudMysqlJwtSpringIntegration.auth.UpdateUserRequest;
import CrudMysqlJwtSpringIntegration.controllers.PointageService;
import CrudMysqlJwtSpringIntegration.user.Pointage;
import CrudMysqlJwtSpringIntegration.user.User;
import CrudMysqlJwtSpringIntegration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService userService;

    private final PointageService pointageService;


    @Autowired
    public AdminController(AuthenticationService userService, PointageService pointageService) {
        this.userService = userService;
        this.pointageService = pointageService;
    }



    @GetMapping
    public String getAdminInformation() {
        return "admin have permission";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        System.out.println("utilisateur was deleted");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-points")
    public ResponseEntity<List<Pointage>> getAllPoints() {
        try {
            List<Pointage> allPoints = pointageService.getAllPoints();
            return ResponseEntity.ok(allPoints);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserRequest updateUserRequest) {
        // Check if the user service is properly autowired
        if (userService == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User service is not properly initialized");
        }

        userService.updateUser(userId, updateUserRequest);
        return ResponseEntity.ok("User updated successfully");
    }
}


