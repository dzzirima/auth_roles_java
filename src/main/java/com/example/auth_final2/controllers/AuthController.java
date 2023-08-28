package com.example.auth_final2.controllers;
import com.example.auth_final2.entity.UserInfo;
import com.example.auth_final2.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public String  register(
           @RequestBody UserInfo userInfo
    ){
        return authService.register(userInfo);
    }
}
