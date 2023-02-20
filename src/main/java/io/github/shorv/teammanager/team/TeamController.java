package io.github.shorv.teammanager.team;

import io.github.shorv.teammanager.team.exception.TeamNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/team")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getTeams(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "sortDir", required = false) String sortDir,
                                               @RequestParam(value = "sortField", required = false) String sortField) {
        return ResponseEntity.ok(teamService.getTeams(page, size, sortDir, sortField));
    }

    @GetMapping("{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamId") Long teamId) throws TeamNotFoundException {
        return teamService.getTeamById(teamId)
                .map(ResponseEntity::ok)
                .orElseThrow(TeamNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Team> addNewTeam(@RequestBody Team team) {
        teamService.addNewTeam(team);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(team);
    }

    @DeleteMapping("{teamId}")
    public void deleteTeamById(@PathVariable("teamId") Long teamId) {
        teamService.deleteTeamById(teamId);
    }
}