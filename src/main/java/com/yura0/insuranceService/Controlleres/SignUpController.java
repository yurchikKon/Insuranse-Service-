package com.yura0.insuranceService.Controlleres;
import com.yura0.insuranceService.Requestes.SignUpRequest;
import com.yura0.insuranceService.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @PostMapping("/reg")
    public ResponseEntity<?> reg(@RequestBody SignUpRequest signUpRequest){
        return userService.createUser(signUpRequest);
    }



    @GetMapping("/signUp")
    public ResponseEntity<?> s(){
        return ResponseEntity.ok("Hi");
    }
}
