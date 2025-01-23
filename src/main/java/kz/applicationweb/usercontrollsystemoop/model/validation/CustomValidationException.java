package kz.applicationweb.usercontrollsystemoop.model.validation;


// created just for exceptions, in service, but I will improve it later
public class CustomValidationException extends RuntimeException {
    public CustomValidationException(String message) {
        super(message);
    }
}