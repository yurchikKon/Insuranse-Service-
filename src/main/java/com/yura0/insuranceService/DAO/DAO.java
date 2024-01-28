package com.yura0.insuranceService.DAO;

import com.yura0.insuranceService.Entities.User;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(Long id);

    List<T> getAll();

    void save(T t);

    void delete(T t);
}
