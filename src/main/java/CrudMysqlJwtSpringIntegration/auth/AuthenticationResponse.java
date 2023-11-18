package CrudMysqlJwtSpringIntegration.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    // Add the builder() method
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }
}
