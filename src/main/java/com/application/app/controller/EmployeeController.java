package com.application.app.controller;

import com.application.app.dto.EmployeeDto;
import com.application.app.dto.LoginDto;
import com.application.app.response.LoginResponse;
import com.application.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto){
        String id = employeeService.saveEmployee(employeeDto);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto){
        LoginResponse loginMessage = employeeService.loginEmployee(loginDto);
        return ResponseEntity.ok(loginMessage);
    }

}
