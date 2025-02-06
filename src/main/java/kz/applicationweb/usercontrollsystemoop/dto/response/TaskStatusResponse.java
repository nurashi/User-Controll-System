package kz.applicationweb.usercontrollsystemoop.dto.response;

import kz.applicationweb.usercontrollsystemoop.model.task.TaskStatus;

public class TaskStatusResponse {
    public Long id;
    public String name;
    public String description;

    public TaskStatusResponse(TaskStatus taskStatus) {
        this.id = taskStatus.getId();
        this.name = taskStatus.getName();
        this.description = taskStatus.getDescription();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
