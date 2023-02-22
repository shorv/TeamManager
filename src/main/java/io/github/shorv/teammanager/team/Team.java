package io.github.shorv.teammanager.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.organization.Organization;
import io.github.shorv.teammanager.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @JsonBackReference
    private Organization organization;
    @OneToMany(mappedBy = "team")
    private Set<Task> tasks;
    @OneToMany(mappedBy = "team")
    private Set<Employee> employees;

    public Team(String name) {
        this.name = name;
        this.organization = null;
        this.tasks = new HashSet<>();
        this.employees = new HashSet<>();
    }

    public void addTask(Task task) {
        task.changeTeam(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setTeam(null);
    }

    public void addEmployee(Employee employee) {
        employee.changeTeam(this);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setTeam(null);
    }
}
