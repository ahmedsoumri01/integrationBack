package CrudMysqlJwtSpringIntegration.admin;


import CrudMysqlJwtSpringIntegration.controllers.PointageService;
import CrudMysqlJwtSpringIntegration.user.Pointage;
import CrudMysqlJwtSpringIntegration.user.User;
import CrudMysqlJwtSpringIntegration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    private final PointageService pointageService;

    @Autowired
    public AdminController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @GetMapping
    public String getAdminInformation(){
        return "admin have permission";
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable Integer id){
        return userRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        System.out.println("utilisateur was deleted");
    }

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
}
