package com.yura0.insuranceService.DAO;

import com.yura0.insuranceService.Entities.Insurance;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Repositories.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsuranceDAO implements DAO<Insurance> {
    private final InsuranceRepository insuranceRepository;

    @Override
    public Optional<Insurance> get(Long id) {
        return insuranceRepository.findInsuranceById(id);
    }

    @Override
    public List<Insurance> getAll() {
        return insuranceRepository.findAll();
    }

    @Override
    public void save(Insurance insurance) {
        insuranceRepository.save(insurance);
    }

    @Override
    public void delete(Insurance insurance) {
        insuranceRepository.delete(insurance);
    }

    public List<Insurance> getAllByUser(User user){
        return insuranceRepository.findAllByUsr(user);
    }
}
