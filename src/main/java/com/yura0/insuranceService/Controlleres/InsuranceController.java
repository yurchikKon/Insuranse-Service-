package com.yura0.insuranceService.Controlleres;

import com.yura0.insuranceService.Requestes.InsuranceRequest;
import com.yura0.insuranceService.Services.InsuranceService;
import com.yura0.insuranceService.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InsuranceRequest insuranceRequest,
                                    @AuthenticationPrincipal UserDetails userDetails){
        return insuranceService.create(insuranceRequest,userService.getUsrByUserDetails(userDetails).get());
    }

    @GetMapping("/shawAll")
    private ResponseEntity<?> showAll(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(insuranceService.shawAll(userService.getUsrByUserDetails(userDetails).get()));
    }
}
