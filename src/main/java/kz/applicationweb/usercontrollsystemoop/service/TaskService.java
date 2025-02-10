package kz.applicationweb.usercontrollsystemoop.service;

import kz.applicationweb.usercontrollsystemoop.dto.request.TaskRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse getTaskById(Long id);
    List<TaskResponse> getAllTasks();
    TaskResponse createTask(TaskRequest requestDto);
    TaskResponse updateTask(Long id, TaskRequest requestDto);
    void deleteTask(Long id);
    List<TaskResponse> getTasksByEmployeeId(Long employeeId);
}