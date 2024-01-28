package com.yura0.insuranceService.Repositories;

import com.yura0.insuranceService.Entities.Pretension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PretensionRepository extends JpaRepository<Pretension,Long> {

    Optional<Pretension> findPretensionById(Long id);

    List<Pretension> findPretensionByUsrId(Long id);

    List<Pretension> findPretensionByEmployeeId(Long id);
}
