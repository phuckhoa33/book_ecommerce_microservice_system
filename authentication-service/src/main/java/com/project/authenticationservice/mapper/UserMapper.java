package com.project.authenticationservice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.authenticationservice.model.User;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    void createNewUser(User user);

    User checkEmailExist(@Param("email") String email);

    User getUser(@Param("userid") Long userid);

    void updateUser(User user);
}
