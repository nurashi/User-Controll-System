package kz.applicationweb.usercontrollsystemoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kz.applicationweb.usercontrollsystemoop.model.user.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}