package kz.applicationweb.usercontrollsystemoop.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Long employeeId;
    private String employeeFullName;
    private Long statusId;
    private String statusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
