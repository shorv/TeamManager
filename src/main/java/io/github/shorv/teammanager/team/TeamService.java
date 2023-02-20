package io.github.shorv.teammanager.team;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    private List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    private List<Team> getPaginatedTeams(int page, int size) {
        return teamRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    private List<Team> getSortedTeams(String sortDirection, String field) {
        return teamRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field));
    }

    private List<Team> getSortedAndPaginatedTeams(int page, int size, String sortDirection, String field) {
        return teamRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field))).getContent();
    }

    public List<Team> getTeams(Integer page, Integer size, String sortDirection, String sortField) {
        boolean pageable = !(page == null || size == null);
        boolean sortable = !(sortDirection == null || sortField == null);

        if (!pageable) {
            if (!sortable) {
                return getAllTeams();
            }

            return getSortedTeams(sortDirection, sortField);
        }

        if (!sortable) {
            return getPaginatedTeams(page, size);
        }


        return getSortedAndPaginatedTeams(page, size, sortDirection, sortField);
    }

    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    public void addNewTeam(Team team) {
        teamRepository.save(team);
    }

    public void deleteTeamById(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
