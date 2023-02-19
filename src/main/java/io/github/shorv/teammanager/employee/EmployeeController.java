package io.github.shorv.teammanager.employee;

import io.github.shorv.teammanager.employee.exception.EmployeeNotFoundException;
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
@RequestMapping(path = "/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "size", required = false) Integer size,
                                                       @RequestParam(value = "sortDir", required = false) String sortDir,
                                                       @RequestParam(value = "sortField", required = false) String sortField) {
        return ResponseEntity.ok(employeeService.getEmployees(page, size, sortDir, sortField));
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(employee);
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }
}
