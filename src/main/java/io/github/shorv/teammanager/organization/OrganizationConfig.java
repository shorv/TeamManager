package io.github.shorv.teammanager.organization;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrganizationConfig {


    @Bean
    public CommandLineRunner organizationCommandLineRunner(OrganizationRepository organizationRepository) {
        return args -> {
            List<Organization> fakeOrganizations = createFakeOrganizations();
            organizationRepository.saveAll(fakeOrganizations);
        };
    }

    public List<Organization> createFakeOrganizations() {
        ArrayList<Organization> organizations = new ArrayList<>();
        List<String> namesList = List.of("PandaSoft", "ChickenSolutions", "PiggyCoders", "RatDev", "RubyOnTrails");

        namesList.forEach(name -> organizations.add(new Organization(name)));

        return organizations;
    }
}
