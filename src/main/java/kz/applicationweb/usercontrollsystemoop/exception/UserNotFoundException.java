package kz.applicationweb.usercontrollsystemoop.exception;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
