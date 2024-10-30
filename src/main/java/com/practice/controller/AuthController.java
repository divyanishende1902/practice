package com.practice.controller;

import com.practice.Exception.UserAlreadyExistsException;
import com.practice.dto.UserDto;
import com.practice.repository.UserRepository;
import com.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto authUser = userService.createUser(userDto);
            return new ResponseEntity<>(authUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}


