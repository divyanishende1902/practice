package com.practice.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {


    private String id;

    @NotBlank
    @Size(max  = 10 , min = 3, message = "name should contain more than 3 characters")
    private String name;

//    @Size( min = 5, max = 30, message="should contain more than 5 letter" )
     @Pattern(regexp = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$", message = "Invalid Email!!")
     @Email(message = "invalid Email!!")
     @NotBlank
    private String email;


    @Length (min = 3, max = 20, message = "username should contain minimum 3 characters")
    private String username;

    @NotBlank
    private String password;
}
