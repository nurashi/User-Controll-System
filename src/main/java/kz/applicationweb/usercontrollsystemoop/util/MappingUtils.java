package kz.applicationweb.usercontrollsystemoop.util;

import org.springframework.stereotype.Component;

import kz.applicationweb.usercontrollsystemoop.dto.request.CreateEmployeeRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;

@Component
public class MappingUtils {

    public Employee convertToEmployee(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        updateEmployeeFromRequest(request, employee);
        return employee;
    }

    public void updateEmployeeFromRequest(CreateEmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setEmail(request.getEmail());
        employee.setPassword(request.getPassword());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setCompany(request.getCompany());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
    }
}