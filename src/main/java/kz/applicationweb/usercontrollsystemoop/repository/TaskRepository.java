package kz.applicationweb.usercontrollsystemoop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kz.applicationweb.usercontrollsystemoop.model.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployeeId(Long employeeId);
    List<Task> findByStatusId(Long statusId);
}
