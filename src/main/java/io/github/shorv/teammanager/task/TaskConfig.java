package io.github.shorv.teammanager.task;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner taskCommandLineRunner(TaskRepository taskRepository) {
        return args -> {
            List<Task> fakeTasks = createFakeTasks();
            taskRepository.saveAll(fakeTasks);
        };
    }

    private List<Task> createFakeTasks() {
        Faker faker = new Faker(Locale.ENGLISH);
        List<Task> tasks = new ArrayList<>();
        List<String> fakeTasks = List.of("Migrate database", "Bump API version", "Fix NPE in HappyDoggoController");

        for (String fakeTask : fakeTasks) {
            tasks.add(new Task(
                    faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    fakeTask,
                    TaskPriority.MEDIUM,
                    LocalDateTime.now()
            ));
        }

        return tasks;
    }
}
