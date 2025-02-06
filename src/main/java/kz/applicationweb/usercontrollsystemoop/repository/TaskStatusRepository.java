package kz.applicationweb.usercontrollsystemoop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kz.applicationweb.usercontrollsystemoop.model.task.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

}