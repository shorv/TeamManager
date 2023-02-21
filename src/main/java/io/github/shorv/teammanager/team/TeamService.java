package io.github.shorv.teammanager.team;

import io.github.shorv.teammanager.PageableAndSortableService;
import io.github.shorv.teammanager.team.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService extends PageableAndSortableService<Team, TeamRepository> {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository repository, TeamRepository teamRepository) {
        super(repository);
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAll(Integer page, Integer size, String sortDirection, String sortField) {
        return super.getAll(page, size, sortDirection, sortField);
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);
    }

    public void addNewTeam(Team team) {
        teamRepository.save(team);
    }

    public void deleteTeamById(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
