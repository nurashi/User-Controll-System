package kz.applicationweb.usercontrollsystemoop.util;

import org.springframework.stereotype.Component;

import kz.applicationweb.usercontrollsystemoop.dto.request.*;
import kz.applicationweb.usercontrollsystemoop.model.user.*;

@Component
public class MappingUtils {

    //#region Employee
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
    //#endregion

    //#region Student
    public Student convertToStudent(CreateStudentRequest request) {
        Student student = new Student();
        updateStudentFromRequest(request, student);
        return student;
    }

    public void updateStudentFromRequest(CreateStudentRequest request, Student student) {
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setUniversity(request.getUniversity());
        student.setGpa(request.getGpa());
    }
    //#endregion
}