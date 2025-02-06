package kz.applicationweb.usercontrollsystemoop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.applicationweb.usercontrollsystemoop.dto.request.CreateTaskRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.TaskResponse;
import kz.applicationweb.usercontrollsystemoop.model.task.Task;
import kz.applicationweb.usercontrollsystemoop.repository.TaskRepository;
import kz.applicationweb.usercontrollsystemoop.security.RequireRole;
import kz.applicationweb.usercontrollsystemoop.service.impl.TaskServiceImpl;

@RestController
@RequestMapping("/api/tasks")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskRepository taskRepository,
            TaskServiceImpl taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequireRole({"admin"})
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request) {
        Task Task = taskService.createTask(request);
        return new TaskResponse(Task);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAllWithStatus()
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    @GetMapping("/by-employee/{id}")
    @RequireRole({"admin", "employee"})
    public List<TaskResponse> getTasksByEmployee(@PathVariable Long id) {
        return taskRepository.findByEmployeeIdWithStatus(id)
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    @RequireRole({"admin"})
    public TaskResponse getTaskById(@PathVariable Long id) {
        Optional<Task> item = taskRepository.findById(id);
        return item.map(TaskResponse::new).orElse(null);
    }

    @PutMapping("/{id}")
    @RequireRole({"admin"})
    public TaskResponse updateTask(@PathVariable Long id,
            @Valid @RequestBody CreateTaskRequest request) {
        Task updatedTask = taskService.updateTask(id, request);
        return new TaskResponse(updatedTask);
    }

    @DeleteMapping("/{id}")
    @RequireRole({"admin"})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}