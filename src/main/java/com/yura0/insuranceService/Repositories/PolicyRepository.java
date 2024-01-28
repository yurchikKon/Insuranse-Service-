package com.yura0.insuranceService.Repositories;

import com.yura0.insuranceService.Entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Optional<Policy> findPolicyById(Long id);
}
