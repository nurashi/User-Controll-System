package kz.applicationweb.usercontrollsystemoop.repository;

import kz.applicationweb.usercontrollsystemoop.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
            SELECT t FROM Task t
            LEFT JOIN FETCH t.status s
            LEFT JOIN Employee e ON t.employeeId = e.id
            WHERE t.id = :id
            """)
    Optional<Task> findByIdWithDetails(Long id);

    @Query("""
            SELECT t FROM Task t
            LEFT JOIN FETCH t.status s
            LEFT JOIN Employee e ON t.employeeId = e.id
            """)
    List<Task> findAllWithDetails();

    @Query("""
            SELECT t FROM Task t
            LEFT JOIN FETCH t.status s
            LEFT JOIN Employee e ON t.employeeId = e.id
            WHERE t.employeeId = :employeeId
            """)
    List<Task> findAllByEmployeeIdWithDetails(Long employeeId);
}