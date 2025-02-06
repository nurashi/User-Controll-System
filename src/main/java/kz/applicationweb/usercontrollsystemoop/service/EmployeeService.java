package kz.applicationweb.usercontrollsystemoop.service;

import kz.applicationweb.usercontrollsystemoop.dto.request.CreateEmployeeRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;

public interface EmployeeService {
    public Employee createEmployee(CreateEmployeeRequest request);

    public Employee updateEmployee(Long id, CreateEmployeeRequest request);

    public void deleteEmployee(Long id);
}
