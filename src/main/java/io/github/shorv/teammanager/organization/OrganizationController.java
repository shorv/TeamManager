package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.team.Team;
import io.github.shorv.teammanager.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/organizations")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<Organization>> getOrganizations(@RequestParam(value = "page", required = false) Integer page,
                                                               @RequestParam(value = "size", required = false) Integer size,
                                                               @RequestParam(value = "sort", required = false) String sortDir,
                                                               @RequestParam(value = "orderBy", required = false) String orderBy) {
        List<Organization> organizations = organizationService.getAll(page, size, sortDir, orderBy);

        return ResponseEntity.ok(organizations);
    }

    @GetMapping("{organizationId}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("organizationId") Long organizationId) {
        Organization organization = organizationService.getOrganizationById(organizationId);

        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<Organization> addNewOrganization(@RequestBody Organization organization) {
        organizationService.addNewOrganization(organization);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(organization.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(organization);
    }

    @DeleteMapping("{organizationId}")
    public void deleteOrganizationById(@PathVariable("organizationId") Long organizationId) {
        organizationService.deleteOrganizationById(organizationId);
    }

    @PutMapping("{organizationId}/teams")
    public void addTeamToOrganization(@PathVariable("organizationId") Long organizationId,
                                      @RequestParam(value = "teamId") Long teamId) {
        organizationService.addNewTeamById(organizationId, teamId);
    }

    @GetMapping("{organizationId}/teams")
    public ResponseEntity<Set<Team>> getOrganizationTeams(@PathVariable("organizationId") Long organizationId) {
        Set<Team> organizationTeams = organizationService.getOrganizationTeams(organizationId);

        return ResponseEntity.ok(organizationTeams);
    }

    @DeleteMapping("{organizationId}/teams")
    public void removeTeamFromOrganizationById(@PathVariable("organizationId") Long organizationId,
                                               @RequestParam(value = "teamId") Long teamId) {
        organizationService.removeTeamFromOrganizationById(organizationId, teamId);
    }

    @PutMapping("{organizationId}/employees")
    public void addEmployeeToOrganizationById(@PathVariable("organizationId") Long organizationId,
                                              @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.addNewEmployeeById(organizationId, employeeId);
    }

    @GetMapping("{organizationId}/employees")
    public ResponseEntity<Set<Employee>> getOrganizationEmployees(@PathVariable("organizationId") Long organizationId) {
        Set<Employee> organizationEmployees = organizationService.getOrganizationEmployees(organizationId);

        return ResponseEntity.ok(organizationEmployees);
    }

    @DeleteMapping("{organizationId}/employees")
    public void removeEmployeeFromOrganizationById(@PathVariable("organizationId") Long organizationId,
                                                   @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.removeEmployeeFromOrganizationById(organizationId, employeeId);
    }
}
