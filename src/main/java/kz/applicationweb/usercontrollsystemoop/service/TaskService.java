package kz.applicationweb.usercontrollsystemoop.service;

import kz.applicationweb.usercontrollsystemoop.dto.request.CreateTaskRequest;
import kz.applicationweb.usercontrollsystemoop.model.task.Task;

public interface TaskService {
    public Task createTask(CreateTaskRequest request);
    public Task updateTask(Long id, CreateTaskRequest request);
    public void deleteTask(Long id);
}
