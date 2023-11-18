package CrudMysqlJwtSpringIntegration.admin;


import CrudMysqlJwtSpringIntegration.user.User;
import CrudMysqlJwtSpringIntegration.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    private UserRepository userRepository;


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

}
