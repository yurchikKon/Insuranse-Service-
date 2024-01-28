package com.yura0.insuranceService.Services;

import com.yura0.insuranceService.DAO.PolicyDAO;
import com.yura0.insuranceService.DAO.PretensionDAO;
import com.yura0.insuranceService.DAO.UserDAO;
import com.yura0.insuranceService.Entities.Policy;
import com.yura0.insuranceService.Entities.Role;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Requestes.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final PolicyDAO policyDAO;
    private final PretensionDAO pretensionDAO;

    public ResponseEntity<?> createUser(SignUpRequest signUpRequest){
        if(userDAO.getByLogin(signUpRequest.getLogin()).isEmpty() && checkEmail(signUpRequest.getMail())){
            System.out.println("s");
            User user = User.builder()
                    .login(signUpRequest.getLogin())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .role(Role.USER)
                    .mail(signUpRequest.getMail())
                    .build();
            Policy policy = Policy.builder()
                    .number(signUpRequest.getNumber())
                    .usr(user)
                    .build();
            userDAO.save(user);
            policyDAO.save(policy);
            return ResponseEntity.ok("Hi, " + signUpRequest.getLogin());
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Optional<User> getUsrByUserDetails(UserDetails userDetails){
        return userDAO.getByLogin(userDetails.getUsername());
    }

    public boolean checkCurrEmployee(User user, Long pretensionId){
        return Objects.equals(pretensionDAO.get(pretensionId).get().getEmployee().getId(), user.getId());
    }

    public boolean checkEmail(String mail){
        return userDAO.getUserByMail(mail).isEmpty();
    }
}
