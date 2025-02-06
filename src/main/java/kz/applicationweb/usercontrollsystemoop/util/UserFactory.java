package kz.applicationweb.usercontrollsystemoop.util;

import org.springframework.stereotype.Component;

import kz.applicationweb.usercontrollsystemoop.dto.request.RegisterRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.model.user.User;

@Component
public class UserFactory {

    public User createUser(RegisterRequest request) {
        if ("student".equalsIgnoreCase(request.getRole())) {
            Student student = new Student();
            student.setUniversity(request.getUniversity());
            student.setGpa(request.getGpa());
            return student;
        } else if ("employee".equalsIgnoreCase(request.getRole())) {
            Employee employee = new Employee();
            employee.setCompany(request.getCompany());
            employee.setPosition(request.getPosition());
            employee.setSalary(request.getSalary());
            return employee;
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }
}
