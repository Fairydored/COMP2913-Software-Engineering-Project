package com.example.helloworld.Mapper;

import com.example.helloworld.model.User;

import java.util.List;

public interface userMapper {
    List<User> selectall();
    User selectzhangsan();

    void insertUser(User user);

}
