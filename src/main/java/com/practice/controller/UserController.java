package com.practice.controller;

import com.practice.Exception.ApiResponseMessage;
import com.practice.dto.UserDto;
import com.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //create
    //http://localhost:8080/user/create
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //update
    //http://localhost:8080/user/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable String id, @RequestBody UserDto userDto ){
        UserDto userDto1 = userService.updateUser(id, userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    //getById
    //http://localhost:8080/user/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
       return new ResponseEntity<>( userService.getSingleUserById(id), HttpStatus.OK);
    }

    //getAll
    //http://localhost:8080/user/all
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        List<UserDto> allUser = userService.getAllUser(pageNumber,  pageSize);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    //getByEmail
    //http://localhost:8080/user/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findUserByEmail( @Valid @PathVariable String email){

      
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //delete
    //http://localhost:8080/user/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        ApiResponseMessage userDeletedSuccessfully = ApiResponseMessage.builder()
                .message("User Deleted Successfully")
                .status(HttpStatus.OK)
                .condition(true)
                .build();
        return new ResponseEntity<>(userDeletedSuccessfully, HttpStatus.OK);
    }

}
