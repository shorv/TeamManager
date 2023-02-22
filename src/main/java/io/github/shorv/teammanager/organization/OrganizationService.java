package io.github.shorv.teammanager.organization;

import io.github.shorv.teammanager.PageableAndSortableService;
import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.employee.EmployeeRepository;
import io.github.shorv.teammanager.employee.exception.EmployeeNotFoundException;
import io.github.shorv.teammanager.organization.exception.OrganizationNotFoundException;
import io.github.shorv.teammanager.team.Team;
import io.github.shorv.teammanager.team.TeamRepository;
import io.github.shorv.teammanager.team.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrganizationService extends PageableAndSortableService<Organization, OrganizationRepository> {

    private final OrganizationRepository organizationRepository;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public OrganizationService(OrganizationRepository repository,
                               OrganizationRepository organizationRepository,
                               TeamRepository teamRepository,
                               EmployeeRepository employeeRepository) {
        super(repository);
        this.organizationRepository = organizationRepository;
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
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
        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        organization.addTeam(team);
        organizationRepository.save(organization);
    }

    public Set<Team> getOrganizationTeams(Long organizationId) {
        Organization organization = getOrganizationById(organizationId);
        return organization.getTeams();
    }

    public void removeTeamFromOrganizationById(Long organizationId, Long teamId) {
        Organization organization = getOrganizationById(organizationId);
        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        organization.removeTeam(team);
        organizationRepository.save(organization);
    }

    public void addNewEmployeeById(Long organizationId, Long employeeId) {
        Organization organization = getOrganizationById(organizationId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);

        organization.addEmployee(employee);
        organizationRepository.save(organization);
    }

    public Set<Employee> getOrganizationEmployees(Long organizationId) {
        Organization organization = getOrganizationById(organizationId);
        return organization.getEmployees();
    }

    public void removeEmployeeFromOrganizationById(Long organizationId, Long employeeId) {
        Organization organization = getOrganizationById(organizationId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);

        organization.removeEmployee(employee);
        organizationRepository.save(organization);
    }
}
