package com.yura0.insuranceService.Services;

import com.yura0.insuranceService.DAO.InsuranceDAO;
import com.yura0.insuranceService.DAO.UserDAO;
import com.yura0.insuranceService.Entities.Insurance;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Entities.InsType;
import com.yura0.insuranceService.Requestes.InsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceDAO insuranceDAO;
    private final UserDAO userDAO;

    public ResponseEntity<?> create(InsuranceRequest insuranceRequest, User user){
        InsType insType = null;
        if(insuranceRequest.getType().equals("EXTRA"))
             insType = InsType.EXTRA;
        else if(insuranceRequest.getType().equals("CAR"))
            insType = InsType.CAR;
        else insType = InsType.BASE;

        Insurance insurance = Insurance.builder()
                .insType(insType)
                .usr(user)
                .cost(insuranceRequest.getCost())
                .build();
        insuranceDAO.save(insurance);
        return ResponseEntity.ok("New insurance was created");
    }

    public ResponseEntity<?> shawAll(User user){
        return ResponseEntity.ok(insuranceDAO.getAllByUser(user));
    }
}
