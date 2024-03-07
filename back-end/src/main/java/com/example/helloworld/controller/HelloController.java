package com.example.helloworld.controller;
import com.example.helloworld.Mapper.UserMapper;

import com.example.helloworld.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class HelloController {
    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/number")
    public String receiveNumber(@RequestParam("a") int a, @RequestParam("b") int b) {
        int sum = a + b;
        return "The sum of a and b is: " + sum;
    }


    @GetMapping("/login")
    public int loginGet( String username,  String password) {
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user != null) {
            return 1; // 登录成功
        } else {
            return 0; // 登录失败
        }
    }


    @PostMapping("/login") // 更改为 @PostMapping
    public int loginPost(@RequestBody User loginUser) { // 使用 @RequestBody 注解获取请求体中的数据
        User user = userMapper.selectByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (user != null) {
            return 1; // 登录成功
        } else {
            return 0; // 登录失败
        }
    }

    /*@GetMapping("/login")
    public int loginGet( String username,  String password) {
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user != null) {
            return 1; // 登录成功
        } else {
            return 0; // 登录失败
        }
    }*/
}

