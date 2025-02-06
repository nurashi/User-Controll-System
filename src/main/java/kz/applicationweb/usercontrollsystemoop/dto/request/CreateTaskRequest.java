package kz.applicationweb.usercontrollsystemoop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateTaskRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @Positive(message = "Employee id is required")
    private Long employeeId;
    @Positive(message = "Status id is required")
    private Long statusId;

    public CreateTaskRequest(String title, String description, Long employeeId, Long statusId) {
        this.title = title;
        this.description = description;
        this.employeeId = employeeId;
        this.statusId = statusId;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") String description) {
        this.description = description;
    }

    public @Positive(message = "Employee id is required") Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@Positive(message = "Employee id is required") Long employeeId) {
        this.employeeId = employeeId;
    }

    public @Positive(message = "Status id is required") Long getStatusId() {
        return statusId;
    }

    public void setStatusId(@Positive(message = "Status id is required") Long statusId) {
        this.statusId = statusId;
    }
}
