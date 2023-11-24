package com.application.app.service.Impl;

import com.application.app.dto.EmployeeDto;
import com.application.app.dto.LoginDto;
import com.application.app.entity.Employee;
import com.application.app.repository.EmployeeRepository;
import com.application.app.response.LoginResponse;
import com.application.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String saveEmployee(EmployeeDto employeeDto){

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getPhone(),
                this.passwordEncoder.encode(employeeDto.getPassword())
        );

        repository.save(employee);
        return employee.getFirstName();

    }

    @Override
    public LoginResponse loginEmployee(LoginDto loginDto) {
        String message = "";
        Employee employee = repository.findByEmail(loginDto.getEmail());
        if(employee != null){
            String password = loginDto.getPassword();
            String encodedPassword = employee.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password,encodedPassword);

            if(isPwdRight){
                Optional<Employee> employee1 = repository.findOneByEmailAndPassword(loginDto.getEmail(),encodedPassword);
                if(employee1.isPresent()){
                    return new LoginResponse("Login success",true);
                }else {
                    return new LoginResponse("Login Failed",false);
                }
            }else {
                return new LoginResponse("Password dose not match",false);
            }

        }else {

            return new LoginResponse("Email does not match",false);
        }
    }

}
