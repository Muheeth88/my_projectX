package com.example.my_projectX;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorld {
    @GetMapping("/intro") 
    String hello() {
        return "Hello Java!";
    }
}
