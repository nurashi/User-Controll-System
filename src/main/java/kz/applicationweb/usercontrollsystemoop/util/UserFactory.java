package kz.applicationweb.usercontrollsystemoop.util;

import org.springframework.stereotype.Component;

import kz.applicationweb.usercontrollsystemoop.dto.request.RegisterRequest;
import kz.applicationweb.usercontrollsystemoop.exception.InvalidRoleException;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.model.user.User;

@Component
public class UserFactory {

    public User createUser(RegisterRequest request) {
        User user = null;
        if ("student".equalsIgnoreCase(request.getRole())) {
            user = new Student();
        } else if ("employee".equalsIgnoreCase(request.getRole())) {
            user = new Employee();
        } else {
            throw new InvalidRoleException("Invalid role");
        }
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
