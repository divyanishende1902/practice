package com.practice.controller;

import com.practice.Exception.UserAlreadyExistsException;
import com.practice.dto.LoginDto;
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

     @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody LoginDto  loginDto){
         boolean status = userService.verifyLogin(loginDto);
//         if(status){
//             return new ResponseEntity<>("Login Successfull !!",HttpStatus.OK);
//         }else{
//             return new ResponseEntity<>("Invalid Username and Password !! try again. ", HttpStatus.UNAUTHORIZED);
//         }

        return status
                ? new   ResponseEntity<>("Login Succesfull !!",HttpStatus.OK)
                : new  ResponseEntity<>("Invalid username and password!! ", HttpStatus.UNAUTHORIZED);




     }

}


