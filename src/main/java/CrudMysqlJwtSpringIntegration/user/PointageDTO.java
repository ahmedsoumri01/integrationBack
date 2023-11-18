package CrudMysqlJwtSpringIntegration.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointageDTO {
    private Integer userId;
    private Double latitude;
    private Double longitude;
}