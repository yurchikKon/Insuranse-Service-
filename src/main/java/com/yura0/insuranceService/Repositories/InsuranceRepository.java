package com.yura0.insuranceService.Repositories;

import com.yura0.insuranceService.Entities.Insurance;
import com.yura0.insuranceService.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    Optional<Insurance> findInsuranceById(Long id);

    List<Insurance> findAllByUsr(User user);
}
