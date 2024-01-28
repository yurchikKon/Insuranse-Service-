package com.yura0.insuranceService.Repositories;

import com.yura0.insuranceService.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByMail(String mail);
}
