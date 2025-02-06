package kz.applicationweb.usercontrollsystemoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}