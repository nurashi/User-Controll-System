package kz.applicationweb.usercontrollsystemoop.dto.response;

import java.time.LocalDateTime;

import kz.applicationweb.usercontrollsystemoop.model.task.Task;
import kz.applicationweb.usercontrollsystemoop.model.task.TaskStatus;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Long employeeId;
    private Long statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String statusName;
    private String statusDescription;

    public TaskResponse(Long id, String title, String description, Long employeeId, Long statusId, LocalDateTime createdAt, LocalDateTime updatedAt, TaskStatus taskStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employeeId = employeeId;
        this.statusId = statusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.statusName = taskStatus.getName();
        this.statusDescription = taskStatus.getDescription();
    }

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.employeeId = task.getEmployeeId();
        this.statusId = task.getStatusId();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        if (task.getStatus() != null) {
            this.statusName = task.getStatus().getName();
            this.statusDescription = task.getStatus().getDescription();
        }
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public Long getStatusId() {
        return statusId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public String getStatusName() {
        return statusName;
    }
    public String getStatusDescription() {
        return statusDescription;
    }
}
