package io.github.shorv.teammanager.employee;

import io.github.shorv.teammanager.employee.exception.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (page == null || size == null) {
            return employeeService.getEmployees();
        }

        return employeeService.getPaginatedEmployees(page, size);
    }

    @GetMapping("{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @PostMapping
    public void addNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
