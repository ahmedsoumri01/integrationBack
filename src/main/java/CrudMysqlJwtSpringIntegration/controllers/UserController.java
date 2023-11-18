package CrudMysqlJwtSpringIntegration.controllers;

import CrudMysqlJwtSpringIntegration.user.PointageDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    private final PointageService pointageService;

    @Autowired
    public UserController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @GetMapping
    public String getUserInformation() {
        return "user has permission";
    }

    @PostMapping("/point")
    public ResponseEntity<Object> addPointage(@RequestBody PointageDTO pointageDTO) {
        try {
            pointageService.addPointage(pointageDTO);
            return ResponseEntity.ok(Map.of("message", "Pointage added successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error adding pointage: " + e.getMessage()));
        }
    }
}
