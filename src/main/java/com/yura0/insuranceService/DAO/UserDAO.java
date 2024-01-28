package com.yura0.insuranceService.DAO;

import com.yura0.insuranceService.Entities.Role;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDAO implements DAO<User>{

    private final UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        return Optional.of(userRepository.findUserById(id))
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getByLogin(String login){
        return Optional.of(userRepository.findUserByLogin(login))
                .orElse(null);
    }


    public User getCurrEmployee(){
        return getAll().stream().filter(employee ->employee.getRole()== Role.MANAGER)
                .min(Comparator.comparing(employee -> employee.getTask().size())).orElse(null);
    }

    public Optional<User> getUserByMail(String mail){
        return userRepository.findUserByMail(mail);
    }
}
