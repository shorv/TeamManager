package io.github.shorv.teammanager.employee;

import io.github.shorv.teammanager.Technology;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int salary;
    private String team;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Technology> techStack;
    private int monthsOfExperience;

    public Employee(String firstName, String lastName, String email, int age, int salary, String team, Set<Technology> techStack, int monthsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.team = team;
        this.techStack = techStack;
        this.monthsOfExperience = monthsOfExperience;
    }
}
