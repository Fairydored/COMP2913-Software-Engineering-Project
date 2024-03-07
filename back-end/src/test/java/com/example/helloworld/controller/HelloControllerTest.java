package com.example.helloworld.controller;

import com.example.helloworld.model.User;
import com.example.helloworld.Mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    // Add setup methods if needed

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }

    @Test
    public void receiveNumberTest() throws Exception {
        mockMvc.perform(post("/number")
                        .param("a", "1")
                        .param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("The sum of a and b is: 3"));
    }

    @Test
    public void loginGetTest() throws Exception {
        // Assuming 'selectByUsernameAndPassword' is correctly mocked
        User dummyUser = new User(1, "username", "password");
        // Mock the behavior of userMapper here, returning dummyUser when the correct username and password are passed

        mockMvc.perform(get("/login")
                        .param("username", "tingwoshuo")
                        .param("password", "xiexieni"))
                .andExpect(status().isOk())
                .andExpect(content().string("1")); // assuming return 1 is for success
    }

    @Test
    public void loginPostTest() throws Exception {
        // Assuming 'selectByUsernameAndPassword' is correctly mocked
        User loginUser = new User(1, "abcde987", "123456");
        // Mock the behavior of userMapper here, returning loginUser when the correct username and password are passed

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("1")); // assuming return 1 is for success
    }


    @Test
    public void checkAllUsersTest() throws Exception {
        // Assuming 'selectAllUsers' is correctly mocked
        List<User> users = Arrays.asList(
                new User(1, "abcde987", "123456"),
                new User(2, "tingwoshuo", "xiexieni")
        );
        // Mock the behavior of userMapper here to return the list 'users'

        mockMvc.perform(post("/checkall"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(users)));
    }

    // Add more tests for other endpoints as necessary

}
