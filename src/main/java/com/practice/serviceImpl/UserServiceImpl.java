package com.practice.serviceImpl;

import com.practice.Exception.ResourceNotFoundException;
import com.practice.dto.UserDto;
import com.practice.entity.User;
import com.practice.repository.UserRepository;
import com.practice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper){
        this.userRepository = userRepository;
        this.mapper = mapper;

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setId(userId);

        // through email checking
        User user = mapToEntity(userDto);
        //Optional<User> opEmail = userRepository.findUserByEmail(userDto.getEmail());
        //Optional<User> opEmail = userRepository.findUserByEmail(user.getEmail());


       // if(opEmail != null){
       //     throw new RuntimeException("user exists");
       // }

        // through username checking
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            throw new RuntimeException("User Exist in DB ");
        }

//        String userId = UUID.randomUUID().toString();
//        userDto.setId(userId);
//
        User savedUser = userRepository.save(user);
        UserDto userDto1 = mapToDto(savedUser);

        return userDto1;
    }

    private UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    private User mapToEntity(UserDto userDto) {
        return  mapper.map(userDto, User.class);
    }

    @Override
    public UserDto updateUser( String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with given id"));
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getSingleUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with given id"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);


        Page<User> page = userRepository.findAll(pageable);

        List<User> allUsers = page.getContent();

        List<UserDto> allUsersDto = allUsers.stream()
                .map((user) -> mapToDto(user))
                .collect(Collectors.toList());
        return allUsersDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ResolutionException("User not found with given email"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
        userRepository.delete(user);
    }
}
