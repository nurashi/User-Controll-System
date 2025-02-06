package kz.applicationweb.usercontrollsystemoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}