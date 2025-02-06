package kz.applicationweb.usercontrollsystemoop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateEmployeeRequest;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.repository.EmployeeRepository;
import kz.applicationweb.usercontrollsystemoop.service.EmployeeService;
import kz.applicationweb.usercontrollsystemoop.util.MappingUtils;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                              MappingUtils mappingUtils) {
        this.employeeRepository = employeeRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest request) {
        Employee employee = mappingUtils.convertToEmployee(request);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, CreateEmployeeRequest request) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow();
        
        mappingUtils.updateEmployeeFromRequest(request, existingEmployee);
        
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow();
        employeeRepository.delete(employee);
    }
}