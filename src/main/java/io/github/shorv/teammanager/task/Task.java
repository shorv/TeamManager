package io.github.shorv.teammanager.task;

import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    private LocalDateTime createdAt;

    public Task(LocalDateTime deadline, String description, TaskPriority priority, LocalDateTime createdAt) {
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.employees = new HashSet<>();
        this.createdAt = createdAt;
    }

    public void changeTeam(Team team) {
        if (this.team != null) {
            this.team.getTasks().remove(this);
        }
        this.team = team;
        this.team.getTasks().add(this);
    }
}
