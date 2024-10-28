package com.practice.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseMessage {

    private String message;
    private HttpStatus status;
    private boolean condition;
}
