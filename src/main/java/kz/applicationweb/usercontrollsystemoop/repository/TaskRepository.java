package kz.applicationweb.usercontrollsystemoop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kz.applicationweb.usercontrollsystemoop.model.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployeeId(Long employeeId);
    List<Task> findByStatusId(Long statusId);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.status WHERE t.employeeId = ?1")
    List<Task> findByEmployeeIdWithStatus(Long employeeId);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.status")
    List<Task> findAllWithStatus();
}
