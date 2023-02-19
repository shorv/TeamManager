package io.github.shorv.teammanager.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
