package com.yura0.insuranceService.DAO;

import com.yura0.insuranceService.Entities.Policy;
import com.yura0.insuranceService.Repositories.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PolicyDAO implements DAO<Policy>{

    private  final PolicyRepository policyRepository;

    @Override
    public Optional<Policy> get(Long id) {
        return Optional.of(policyRepository.findPolicyById(id)).orElse(null);
    }

    @Override
    public List<Policy> getAll() {
        return policyRepository.findAll();
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public void delete(Policy policy) {
        policyRepository.delete(policy);
    }
}
