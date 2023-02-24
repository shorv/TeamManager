package io.github.shorv.teammanager.organization.controller;

import io.github.shorv.teammanager.organization.Organization;
import io.github.shorv.teammanager.organization.OrganizationService;
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
@RequestMapping(path = "/api/v1/organizations")
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

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
}
