package kz.applicationweb.usercontrollsystemoop.util;

import kz.applicationweb.usercontrollsystemoop.model.user.Admin;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.model.user.User;

public class UserRoles {
    public static String getRole(User user) {
        if (user instanceof Admin) {
            return "admin";
        } else if (user instanceof Employee) {
            return "employee";
        } else if (user instanceof Student) {
            return "student";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
