package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.team.Team;
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

    @OneToMany(mappedBy = "organization")
    private Set<Team> teams;
    @OneToMany(mappedBy = "organization")
    private Set<Employee> employees;

    public Organization(String name) {
        this.name = name;
        this.teams = new HashSet<>();
        this.employees = new HashSet<>();
    }
}
