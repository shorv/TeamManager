package io.github.shorv.teammanager.employee;

import com.github.javafaker.Faker;
import io.github.shorv.teammanager.Technology;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Faker faker = new Faker(Locale.ENGLISH);

            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                employees.add(new Employee(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        new Random().nextInt(41) + 20,
                        new Random().nextInt(5000) + 3000,
                        faker.color().name(),
                        Set.of(Technology.SPRING_BOOT),
                        new Random().nextInt(60) + 1
                ));
            }

            employeeRepository.saveAll(employees);
        };
    }
}
