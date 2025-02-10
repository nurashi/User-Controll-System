package kz.applicationweb.usercontrollsystemoop.controller;

import kz.applicationweb.usercontrollsystemoop.dto.request.TaskRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.TaskResponse;
import kz.applicationweb.usercontrollsystemoop.security.RequireRole;
import kz.applicationweb.usercontrollsystemoop.service.impl.TaskServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl studentService) {
        this.taskService = studentService;
    }

    @GetMapping("/{id}")
    @RequireRole({"admin"})
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    @RequireRole({"admin"})
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    @RequireRole({"admin"})
    public ResponseEntity<TaskResponse> createTask(@Validated @RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.createTask(taskRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RequireRole({"admin"})
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Validated @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequest));
    }

    @DeleteMapping("/{id}")
    @RequireRole({"admin"})
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TaskResponse>> getTasksByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(taskService.getTasksByEmployeeId(employeeId));
    }
}