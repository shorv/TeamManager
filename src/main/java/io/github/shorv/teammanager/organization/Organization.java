package io.github.shorv.teammanager.organization;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.team.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Team> teams;
    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    public Organization(String name) {
        this.name = name;
        this.teams = new HashSet<>();
        this.employees = new HashSet<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setOrganization(this);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        team.setOrganization(null);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setOrganization(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setOrganization(null);
    }
}
