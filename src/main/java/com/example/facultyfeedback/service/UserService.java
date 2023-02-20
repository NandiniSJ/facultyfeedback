package com.example.facultyfeedback.service;

import com.example.facultyfeedback.entity.User;
import com.example.facultyfeedback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
