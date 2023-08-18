package com.project.authenticationservice.service.impl;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.authenticationservice.dto.EmailDetailsDTO;
import com.project.authenticationservice.dto.EmailInputDataDTO;
import com.project.authenticationservice.mapper.UserMapper;
import com.project.authenticationservice.model.User;
import com.project.authenticationservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void createNewUser(User user) {
        userMapper.createNewUser(user);
    }

    @Override
    public String register(User user) {
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser != null) {
            return "Account is exist";
        }
        user.setId(createRandomId(10));
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(user.getPassword())));
        this.createNewUser(user);
        return "Register is successfully";
    }

    @Override
    public String login(User user) {
        User oldUser = userMapper.checkEmailExist(user.getEmail());
        if (oldUser == null) {
            return "Account is not exist";
        }

        if (!passwordEncoder.matches(CharBuffer.wrap(user.getPassword()), oldUser.getPassword())) {
            return "Password is not match";
        }
        return "Login is successfuly";
    }

    @Override
    public String sendEmail(EmailInputDataDTO request) {
        String message = "Send Email is successfully";
        try {
            // PaymentEmailDetailsDTO payment = new PaymentEmailDetailsDTO();
            EmailDetailsDTO details = new EmailDetailsDTO();
            details.setRecipient(request.getEmail());
            details.setSubject(message);
            // payment.setEmailDetails(details);
            // emailService.sendSimpleMail(payment, "resetPasswordLetter");
        } catch (Exception e) {
            message = "Send email is failed";
        }

        return message;
    }

    @Override
    public String updateUser(User user) {
        String message = "Update user is successfully";
        try {
            if (user.getPassword().length() < 50) {
                user.setPassword(passwordEncoder.encode(CharBuffer.wrap(user.getPassword())));
            }
            User findedUser = userMapper.checkEmailExist(user.getEmail());
            user.setId(findedUser.getId());
            userMapper.updateUser(user);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            message = "Update user is failed";
        }
        return message;
    }

    @Override
    public User getUserById(String userid) {
        return userMapper.getUser(Long.parseLong(userid));
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.checkEmailExist(email);
    }

    private long createRandomId(int desiredLength) {
        if (desiredLength <= 0) {
            throw new IllegalArgumentException("Desired length must be a positive integer.");
        }

        Random random = new Random();
        long min = (long) Math.pow(10, desiredLength - 1);
        long max = (long) Math.pow(10, desiredLength) - 1;

        return min + random.nextLong() % (max - min + 1);
    }

}
