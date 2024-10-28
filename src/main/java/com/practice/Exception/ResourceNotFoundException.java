package com.practice.Exception;

import org.springframework.context.annotation.Configuration;


public class ResourceNotFoundException extends RuntimeException{

     public ResourceNotFoundException(String message){
         super();
     }

}
