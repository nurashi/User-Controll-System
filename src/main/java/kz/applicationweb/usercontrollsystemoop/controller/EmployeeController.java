package kz.applicationweb.usercontrollsystemoop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateEmployeeRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.EmployeeResponse;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.repository.EmployeeRepository;
import kz.applicationweb.usercontrollsystemoop.service.impl.EmployeeServiceImpl;
import kz.applicationweb.usercontrollsystemoop.security.RequireRole;

@RestController
@RequestMapping("/api/employees")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository,
            EmployeeServiceImpl employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequireRole({"admin"})
    public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request);
        return new EmployeeResponse(employee);
    }

    @GetMapping
    @RequireRole({"admin"})
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    @RequireRole({"admin"})
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        Optional<Employee> item = employeeRepository.findById(id);
        return item.map(EmployeeResponse::new).orElse(null);
    }

    @PutMapping("/{id}")
    @RequireRole({"admin", "employee"})
    public EmployeeResponse updateEmployee(@PathVariable Long id,
            @Valid @RequestBody CreateEmployeeRequest request) {
        Employee updatedEmployee = employeeService.updateEmployee(id, request);
        return new EmployeeResponse(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequireRole({"admin"})
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}