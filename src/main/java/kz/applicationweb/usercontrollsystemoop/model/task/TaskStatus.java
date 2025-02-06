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

    public TaskStatus() {

    }
    public TaskStatus(String name, String description) {
        this.name = name;
        this.description = description;
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
