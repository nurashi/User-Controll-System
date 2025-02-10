package kz.applicationweb.usercontrollsystemoop.dto.request;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private Long employeeId;
    private Long statusId;
}
