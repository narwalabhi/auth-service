package com.narwal.authservice.service;

import com.narwal.authservice.model.User;
import com.narwal.authservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User getUserByEmail(String email){
        Optional<User> user = userRepo.findUserByEmail(email);
        System.out.println("Service " + email);
        return user.orElse(null);
    }

}
