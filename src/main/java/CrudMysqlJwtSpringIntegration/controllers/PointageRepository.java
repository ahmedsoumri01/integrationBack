package CrudMysqlJwtSpringIntegration.controllers;

import CrudMysqlJwtSpringIntegration.user.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PointageRepository extends JpaRepository<Pointage, Long> {
    List<Pointage> findByPointedDate(LocalDateTime pointedDate);
}