package com.example.helloworld.controller;

import com.example.helloworld.model.NumberInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/number")
    public String receiveNumber(@RequestBody NumberInput input) {
        int sum = input.getA() + input.getB();
        return "The sum of a and b is: " + sum;
    }

    /*@PostMapping(value = "/number", consumes = "multipart/form-data")
    public String receiveFormData(@RequestPart("a") int a, @RequestPart("b") int b) {
        int sum = a + b;
        return "The sum of a and b is: " + sum;
    }*/


}

