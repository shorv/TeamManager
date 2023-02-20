package io.github.shorv.teammanager.team;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class TeamConfig {

    @Bean
    CommandLineRunner teamCommandLineRunner(TeamRepository teamRepository) {
        return args -> {
            List<Team> fakeTeams = createFakeTeams(5);
            teamRepository.saveAll(fakeTeams);
        };
    }


    private List<Team> createFakeTeams(int amount) {
        Faker faker = new Faker(Locale.ENGLISH);
        List<Team> teams = new ArrayList<>();

        int i = 0;
        while(i < amount){
            String fakeName = faker.color().name();
            if(teamExists(teams, fakeName)){
                continue;
            }

            teams.add(new Team(fakeName));
            i++;
        }

        return teams;
    }

    boolean teamExists(List<Team> teams, String name){
        return teams.stream()
                .anyMatch(team -> team.getName().equals(name));
    }
}
