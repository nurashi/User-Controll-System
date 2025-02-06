package kz.applicationweb.usercontrollsystemoop.model.task;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task_statuses")
@Data
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
