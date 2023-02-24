package io.github.shorv.teammanager.organization.controller;

import io.github.shorv.teammanager.employee.Employee;
import io.github.shorv.teammanager.organization.OrganizationService;
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
@RequestMapping(path = "/api/v1/organizations/{organizationId}/employees")
@AllArgsConstructor
public class OrganizationEmployeesController {

    private final OrganizationService organizationService;

    @PutMapping()
    public void addEmployeeToOrganizationById(@PathVariable("organizationId") Long organizationId,
                                              @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.addNewEmployeeById(organizationId, employeeId);
    }

    @GetMapping()
    public ResponseEntity<Set<Employee>> getOrganizationEmployees(@PathVariable("organizationId") Long organizationId) {
        Set<Employee> organizationEmployees = organizationService.getOrganizationEmployees(organizationId);

        return ResponseEntity.ok(organizationEmployees);
    }

    @DeleteMapping()
    public void removeEmployeeFromOrganizationById(@PathVariable("organizationId") Long organizationId,
                                                   @RequestParam(value = "employeeId") Long employeeId) {
        organizationService.removeEmployeeFromOrganizationById(organizationId, employeeId);
    }
}
