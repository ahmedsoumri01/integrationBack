package CrudMysqlJwtSpringIntegration.user;

import CrudMysqlJwtSpringIntegration.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pointage")
@EntityListeners(AuditingEntityListener.class)
public class Pointage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime pointedDate;

    private Double latitude;
    private Double longitude;

    // Constructors, getters, setters, etc.
}