package io.github.shorv.teammanager.employee;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getPaginatedEmployees(int page, int size) {
        return employeeRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent();
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
