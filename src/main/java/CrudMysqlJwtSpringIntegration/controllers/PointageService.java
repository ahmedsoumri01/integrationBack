package CrudMysqlJwtSpringIntegration.controllers;


import CrudMysqlJwtSpringIntegration.user.Pointage;
import CrudMysqlJwtSpringIntegration.user.PointageDTO;
import CrudMysqlJwtSpringIntegration.user.User;
import CrudMysqlJwtSpringIntegration.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointageService {

    private final PointageRepository pointageRepository;
    private final UserRepository userRepository;

    @Autowired
    public PointageService(PointageRepository pointageRepository, UserRepository userRepository) {
        this.pointageRepository = pointageRepository;
        this.userRepository = userRepository;
    }

    public void addPointage(PointageDTO pointageDTO) {
        User user = userRepository.findById(pointageDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Pointage pointage = Pointage.builder()
                .user(user)
                .latitude(pointageDTO.getLatitude())
                .longitude(pointageDTO.getLongitude())
                .pointedDate(LocalDateTime.now()) // Set current timestamp
                .build();

        pointageRepository.save(pointage);
    }
    public List<Pointage> getAllPoints() {
        return pointageRepository.findAll();
    }


}