package com.yura0.insuranceService.DAO;

import com.yura0.insuranceService.Entities.Pretension;
import com.yura0.insuranceService.Repositories.PretensionRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PretensionDAO implements DAO<Pretension>{

    private final PretensionRepository pretensionRepository;

    @Override
    public Optional<Pretension> get(Long id) {
        return Optional.of(pretensionRepository.findPretensionById(id)).orElse(null);
    }

    @Override
    public List<Pretension> getAll() {
        return pretensionRepository.findAll();
    }

    @Override
    public void save(Pretension pretension) {
        pretensionRepository.save(pretension);
    }

    @Override
    public void delete(Pretension pretension) {
        pretensionRepository.delete(pretension);
    }

    public List<Pretension> getByUsrId(Long id){
        return pretensionRepository.findPretensionByUsrId(id);
    }

    public List<Pretension> getByEmployeeId(Long id){
        return pretensionRepository.findPretensionByEmployeeId(id);
    }
}
