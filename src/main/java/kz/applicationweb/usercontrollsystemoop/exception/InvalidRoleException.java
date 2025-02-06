package kz.applicationweb.usercontrollsystemoop.exception;

public class InvalidRoleException extends IllegalArgumentException {
    public InvalidRoleException() {
        super("Invalid role");
    }

    public InvalidRoleException(String message) {
        super(message);
    }
}
