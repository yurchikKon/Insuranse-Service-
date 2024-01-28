package com.yura0.insuranceService.Controlleres;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class baseController {


    @GetMapping(name = "/hello")
    public ResponseEntity<?> hi(){
        return ResponseEntity.ok("h");
    }
}
