package io.github.shorv.teammanager.organization.controller;

import io.github.shorv.teammanager.organization.OrganizationService;
import io.github.shorv.teammanager.task.Task;
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
@RequestMapping(path = "/api/v1/organizations/{organizationId}/tasks")
@AllArgsConstructor
public class OrganizationTaskController {

    private final OrganizationService organizationService;

    @PutMapping()
    public void addTaskToOrganizationById(@PathVariable("organizationId") Long organizationId,
                                          @RequestParam(value = "taskId") Long taskId) {
        organizationService.addNewTaskById(organizationId, taskId);
    }

    @GetMapping()
    public ResponseEntity<Set<Task>> getOrganizationTasks(@PathVariable("organizationId") Long organizationId) {
        Set<Task> organizationTasks = organizationService.getOrganizationTasks(organizationId);

        return ResponseEntity.ok(organizationTasks);
    }

    @DeleteMapping()
    public void removeTaskFromOrganizationById(@PathVariable("organizationId") Long organizationId,
                                               @RequestParam(value = "taskId") Long taskId) {
        organizationService.removeTaskFromOrganizationById(organizationId, taskId);
    }
}
