package io.github.shorv.teammanager.task;

import io.github.shorv.teammanager.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime deadline;
    private String description;
    private TaskPriority priority;

    @ManyToMany(mappedBy = "tasks")
    private Set<Employee> employees;
    private String team;
    private LocalDateTime createdAt;
    private String comments;

    public Task(Long id, LocalDateTime deadline, String description, TaskPriority priority, String team, LocalDateTime createdAt, String comments) {
        this.id = id;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.team = team;
        this.createdAt = createdAt;
        this.comments = comments;
    }
}
