package kz.applicationweb.usercontrollsystemoop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.applicationweb.usercontrollsystemoop.dto.response.TaskStatusResponse;
import kz.applicationweb.usercontrollsystemoop.repository.TaskStatusRepository;
import kz.applicationweb.usercontrollsystemoop.security.RequireRole;

@RestController
@RequestMapping("/api/task-statuses")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Task Statuses")
public class TaskStatusController {

    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskStatusController(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @GetMapping
    @RequireRole({"admin", "employee"})
    public List<TaskStatusResponse> getAllTaskstatuses() {
        return taskStatusRepository.findAll()
                .stream()
                .map(TaskStatusResponse::new)
                .toList();
    }
}