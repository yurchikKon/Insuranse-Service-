package com.yura0.insuranceService.Controlleres;

import com.yura0.insuranceService.Requestes.EmployeeCreationRequest;
import com.yura0.insuranceService.Services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest,
                                            @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails.getAuthorities());
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return employeeService.createEmployee(employeeCreationRequest);
        else return ResponseEntity.ok("You are not an admin, sorry");
    }
}
