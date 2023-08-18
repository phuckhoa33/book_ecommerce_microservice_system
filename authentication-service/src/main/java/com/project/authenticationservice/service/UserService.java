package com.project.authenticationservice.service;

import java.util.List;

import com.project.authenticationservice.dto.EmailInputDataDTO;
import com.project.authenticationservice.model.User;

public interface UserService {
    List<User> getAllUsers();

    void createNewUser(User user);

    String register(User user);

    String login(User user);

    String sendEmail(EmailInputDataDTO request);

    String updateUser(User user);

    User getUserById(String userid);

    User getUserByEmail(String email);
}
