package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
 
    @Autowired 
    private UserRepository customerRepo;
     
    public void changePassword(User user, String newPassword) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        
        user.setPassword(encodedPassword);
        user.setFechaVigencia(LocalDateTime.now());
        user.setFechaModificacion(LocalDateTime.now().plusMonths(1));
         
        customerRepo.save(user);
    }
}