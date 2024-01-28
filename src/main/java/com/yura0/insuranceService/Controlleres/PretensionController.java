package com.yura0.insuranceService.Controlleres;

import com.yura0.insuranceService.DAO.PretensionDAO;
import com.yura0.insuranceService.DAO.UserDAO;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Requestes.CurrPretensionRequest;
import com.yura0.insuranceService.Requestes.PretensionRequest;
import com.yura0.insuranceService.Services.PretensionService;
import com.yura0.insuranceService.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pretension")
@RequiredArgsConstructor
public class PretensionController {

    private final PretensionService pretensionService;
    private  final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestBody PretensionRequest pretensionRequest){
        return pretensionService.createPretension((User)userDetails, pretensionRequest);
    }

    @GetMapping("/showAll")
    public ResponseEntity<?> showAll(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER"))){
            return ResponseEntity.ok(pretensionService.showPretensions(userDetails));
        }
        else if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("MANAGER"))){
            return ResponseEntity.ok(pretensionService.showTasks(userDetails));
        }
        else return ResponseEntity.ok("Admin dont have any pretensions");
    }
    @PostMapping("/showAll/currentPretension")
    public ResponseEntity<?> answer(@RequestBody CurrPretensionRequest currPretensionRequest,
                                    @AuthenticationPrincipal UserDetails userDetails){
        Long longId = Long.parseLong(currPretensionRequest.getId());
        Optional<User> user = userService.getUsrByUserDetails(userDetails);
        if(userService.checkCurrEmployee(user.get(),longId)) {
            return pretensionService.answer(longId, currPretensionRequest.getAnswer());
        }
        else return ResponseEntity.ok("It is not your task");
    }

    @PostMapping("/showAll/dropCheckedPretensions")
    public ResponseEntity<?> drop(@AuthenticationPrincipal UserDetails userDetails){
        return pretensionService.drop(userService.getUsrByUserDetails(userDetails).get());
    }
}
