package kz.applicationweb.usercontrollsystemoop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateEmployeeRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.EmployeeResponse;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.repository.EmployeeRepository;
import kz.applicationweb.usercontrollsystemoop.service.impl.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, 
                             EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request);
        return new EmployeeResponse(employee);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        Optional<Employee> item = employeeRepository.findById(id);
        return item.map(EmployeeResponse::new).orElse(null);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id, 
                                          @Valid @RequestBody CreateEmployeeRequest request) {
        Employee updatedEmployee = employeeService.updateEmployee(id, request);
        return new EmployeeResponse(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}