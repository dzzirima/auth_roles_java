package com.example.auth_final2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {
    @GetMapping("/messages")
    public ResponseEntity<List<String>> message(){
        return  ResponseEntity.ok(Arrays.asList("first" , "second"));
    }


    @GetMapping("/admin-messages")
    public ResponseEntity<List<String>> adminMessage(){
        return  ResponseEntity.ok(Arrays.asList("admin-secret" , "secret-admin"));
    }


    @GetMapping("/user-messages")
    public ResponseEntity<List<String>> userMessage(){
        return  ResponseEntity.ok(Arrays.asList("user-message" , "users dont have secrets"));
    }
}