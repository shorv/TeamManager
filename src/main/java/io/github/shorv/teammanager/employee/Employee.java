package io.github.shorv.teammanager.employee;

import io.github.shorv.teammanager.Technology;
import io.github.shorv.teammanager.task.Task;
import io.github.shorv.teammanager.team.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Technology> techStack;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "employees_tasks",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks;

    public Employee(String firstName, String lastName, String email, int age, Set<Technology> techStack) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.techStack = techStack;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.getEmployees().add(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.getEmployees().remove(this);
    }

    public void changeTeam(Team team) {
        if (this.team != null) {
            this.team.getEmployees().remove(this);
        }
        this.team = team;
        this.team.getEmployees().add(this);
    }
}
