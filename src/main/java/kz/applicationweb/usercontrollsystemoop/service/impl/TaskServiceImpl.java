package kz.applicationweb.usercontrollsystemoop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateTaskRequest;
import kz.applicationweb.usercontrollsystemoop.model.task.Task;
import kz.applicationweb.usercontrollsystemoop.repository.TaskRepository;
import kz.applicationweb.usercontrollsystemoop.service.TaskService;
import kz.applicationweb.usercontrollsystemoop.util.MappingUtils;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, MappingUtils mappingUtils) {
        this.taskRepository = taskRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Task task = mappingUtils.convertToTask(request);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, CreateTaskRequest request) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow();

        mappingUtils.updateTaskFromRequest(request, existingTask);

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task Task = taskRepository.findById(id)
                .orElseThrow();
        taskRepository.delete(Task);
    }
}
