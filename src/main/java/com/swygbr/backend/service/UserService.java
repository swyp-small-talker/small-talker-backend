package com.swygbr.backend.service;

import com.swygbr.backend.entity.Userinfo;
import com.swygbr.backend.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Query(value = "select u.id, u.password, u.username from user u", nativeQuery = true)
    public List<Userinfo> getAllUsers() {
        return userRepository.findAll();
    }

    public Userinfo getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Userinfo createUser(Userinfo userinfo) {
        return userRepository.save(userinfo);
    }

    public Userinfo updateUser(Userinfo userinfo) {
        return userRepository.save(userinfo);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
