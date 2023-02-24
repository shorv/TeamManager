package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.PageableAndSortableService;
import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.employee.EmployeeService;
import io.github.shorv.teammanager.organization.exception.OrganizationNotFoundException;
import io.github.shorv.teammanager.team.Team;
import io.github.shorv.teammanager.team.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrganizationService extends PageableAndSortableService<Organization, OrganizationRepository> {

    private final OrganizationRepository organizationRepository;
    private final TeamService teamService;
    private final EmployeeService employeeService;

    public OrganizationService(OrganizationRepository repository,
                               OrganizationRepository organizationRepository,
                               TeamService teamService,
                               EmployeeService employeeService) {
        super(repository);
        this.organizationRepository = organizationRepository;
        this.teamService = teamService;
        this.employeeService = employeeService;
    }

    @Override
    public List<Organization> getAll(Integer page, Integer size, String sortDirection, String sortField) {
        return super.getAll(page, size, sortDirection, sortField);
    }

    public Organization getOrganizationById(Long organizationId) {
        return organizationRepository.findById(organizationId)
                .orElseThrow(OrganizationNotFoundException::new);
    }

    public void addNewOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public void deleteOrganizationById(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }

    public void addNewTeamById(Long organizationId, Long teamId) {
        Organization organization = getOrganizationById(organizationId);
        Team team = teamService.getTeamById(teamId);

        organization.addTeam(team);
        organizationRepository.save(organization);
    }

    public Set<Team> getOrganizationTeams(Long organizationId) {
        Organization organization = getOrganizationById(organizationId);
        return organization.getTeams();
    }

    public void removeTeamFromOrganizationById(Long organizationId, Long teamId) {
        Organization organization = getOrganizationById(organizationId);
        Team team = teamService.getTeamById(teamId);

        organization.removeTeam(team);
        organizationRepository.save(organization);
    }

    public void addNewEmployeeById(Long organizationId, Long employeeId) {
        Organization organization = getOrganizationById(organizationId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        organization.addEmployee(employee);
        organizationRepository.save(organization);
    }

    public Set<Employee> getOrganizationEmployees(Long organizationId) {
        Organization organization = getOrganizationById(organizationId);
        return organization.getEmployees();
    }

    public void removeEmployeeFromOrganizationById(Long organizationId, Long employeeId) {
        Organization organization = getOrganizationById(organizationId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        organization.removeEmployee(employee);
        organizationRepository.save(organization);
    }
}
