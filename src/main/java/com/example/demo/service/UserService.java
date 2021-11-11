package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;

@Service
@Transactional
public class UserService {
 
    @Autowired 
    private UserRepository userRepository;
     
    public void changePassword(User updateUser, String newPassword) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        
        updateUser.setPassword(encodedPassword);
        updateUser.setFechaModificacion(LocalDateTime.now().plusMonths(1));
         
        userRepository.save(updateUser);
    }
}