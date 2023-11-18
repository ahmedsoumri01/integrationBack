package CrudMysqlJwtSpringIntegration.auth;

// Create a custom exception class for user not found
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
