package com.application.app.service;

import com.application.app.dto.EmployeeDto;
import com.application.app.dto.LoginDto;
import com.application.app.entity.Employee;
import com.application.app.response.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeService {
    String saveEmployee(EmployeeDto employeeDto);

    LoginResponse loginEmployee(LoginDto loginDto);
}
