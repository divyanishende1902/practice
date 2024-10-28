package com.practice.service;

import com.practice.dto.UserDto;

import java.util.List;

public interface UserService
{
    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(String id, UserDto userDto );

    //by one id
    UserDto getSingleUserById(String id);


    //get All User
    List<UserDto> getAllUser(int pageNumber , int pageSize);

    //Get user by Email
    UserDto getUserByEmail(String email);

   //delete user
    void deleteUser(String id);

}
