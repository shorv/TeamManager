package io.github.shorv.teammanager.employee;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    private List<Employee> getPaginatedEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    private List<Employee> getSortedEmployees(String sortDirection, String field) {
        return employeeRepository.findAll(Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field));
    }

    private List<Employee> getSortedAndPaginatedEmployees(int page, int size, String sortDirection, String field) {
        return employeeRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), field))).getContent();
    }

    public List<Employee> getEmployees(Integer page, Integer size, String sortDirection, String sortField) {
        if (page == null || size == null) {
            if (sortDirection == null || sortField == null) {
                return getAllEmployees();
            }

            return getSortedEmployees(sortDirection, sortField);
        }

        if (sortDirection == null || sortField == null) {
            return getPaginatedEmployees(page, size);
        }


        return getSortedAndPaginatedEmployees(page, size, sortDirection, sortField);
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
