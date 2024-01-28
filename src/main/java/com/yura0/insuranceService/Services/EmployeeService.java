package com.yura0.insuranceService.Services;

import com.yura0.insuranceService.DAO.UserDAO;
import com.yura0.insuranceService.Entities.Role;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Requestes.EmployeeCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createEmployee(EmployeeCreationRequest employeeCreationRequest){
        if(userDAO.getByLogin(employeeCreationRequest.getLogin()).isEmpty()) {
             User user = User.builder()
                    .login(employeeCreationRequest.getLogin())
                    .password(passwordEncoder.encode(employeeCreationRequest.getPassword()))
                    .role(Role.MANAGER)
                    .build();
            userDAO.save(user);
            return ResponseEntity.ok("New employee was created");
        }
        else return ResponseEntity.ok("Login has been already in use");
    }
}
