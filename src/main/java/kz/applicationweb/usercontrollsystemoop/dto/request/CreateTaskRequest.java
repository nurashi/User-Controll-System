package kz.applicationweb.usercontrollsystemoop.dto.request;

public class CreateTaskRequest {
    private String title;
    private String description;
    private Long employeeId;
    private Long statusId;

    public CreateTaskRequest(String title, String description, Long employeeId, Long statusId) {
        this.title = title;
        this.description = description;
        this.employeeId = employeeId;
        this.statusId = statusId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Long getStatusId() {
        return statusId;
    }
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
