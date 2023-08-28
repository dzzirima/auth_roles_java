package com.example.auth_final2.service;

import com.example.auth_final2.entity.UserInfo;
import com.example.auth_final2.repositoy.UserInforRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    final  private UserInforRepository repository;


    final private  PasswordEncoder passwordEncoder;

    public AuthService(UserInforRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        repository.save(userInfo);

        return  "User Saved Successfully ";

    }
}
