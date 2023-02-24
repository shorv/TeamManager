package io.github.shorv.teammanager.organization.controller;

import io.github.shorv.teammanager.organization.OrganizationService;
import io.github.shorv.teammanager.team.Team;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/organizations/{organizationId}/teams")
@AllArgsConstructor
public class OrganizationTeamsController {

    private final OrganizationService organizationService;

    @GetMapping()
    public ResponseEntity<Set<Team>> getOrganizationTeams(@PathVariable("organizationId") Long organizationId) {
        Set<Team> organizationTeams = organizationService.getOrganizationTeams(organizationId);

        return ResponseEntity.ok(organizationTeams);
    }

    @PutMapping()
    public void addTeamToOrganization(@PathVariable("organizationId") Long organizationId,
                                      @RequestParam(value = "teamId") Long teamId) {
        organizationService.addNewTeamById(organizationId, teamId);
    }

    @DeleteMapping()
    public void removeTeamFromOrganizationById(@PathVariable("organizationId") Long organizationId,
                                               @RequestParam(value = "teamId") Long teamId) {
        organizationService.removeTeamFromOrganizationById(organizationId, teamId);
    }

    @PutMapping("/{teamId}")
    public void addEmployeeToTeam(@PathVariable("organizationId") Long organizationId,
                                  @PathVariable("teamId") Long teamId,
                                  @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.addEmployeeToTeam(organizationId, teamId, employeeId);
    }

    @DeleteMapping("/{teamId}")
    public void removeEmployeeFromTeam(@PathVariable("organizationId") Long organizationId,
                                       @PathVariable("teamId") Long teamId,
                                       @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.removeEmployeeFromTeam(organizationId, teamId, employeeId);
    }
}
