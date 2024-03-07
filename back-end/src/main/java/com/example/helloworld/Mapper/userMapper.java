package com.example.helloworld.Mapper;

import com.example.helloworld.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> selectall();
    User selectzhangsan();

    void insertUser(User user);
    User selectByUsernameAndPassword(@Param("username") String username,
                                     @Param("password") String password);

}
