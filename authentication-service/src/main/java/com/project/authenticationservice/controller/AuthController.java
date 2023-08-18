package com.project.authenticationservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.authenticationservice.config.JwtService;
import com.project.authenticationservice.dto.AuthenticationDTO;
import com.project.authenticationservice.dto.EmailInputDataDTO;
import com.project.authenticationservice.model.User;
import com.project.authenticationservice.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String message = userService.register(user);

            String token = null;
            if (message.equals("Register is successfully")) {

                token = jwtService.generateToken(user);
            }
            AuthenticationDTO data = new AuthenticationDTO(token, message);
            result.put("success", true);
            result.put("message", "Success to call API register");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API register");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String message = userService.login(user);
            String token = null;
            if (message.equals("Login is successfuly")) {

                token = jwtService.generateToken(user);
            }
            AuthenticationDTO data = new AuthenticationDTO(token, message);
            result.put("success", true);
            result.put("message", "Success to call API GetAllUsers");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API GetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("forgotPassword")
    ResponseEntity<?> sendEmail(@RequestBody EmailInputDataDTO request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.sendEmail(request);
            result.put("success", true);
            result.put("message", "Success to call API GetAllUsers");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API GetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    ResponseEntity<?> resetPassword(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.updateUser(user);
            result.put("success", true);
            result.put("message", "Success to call API GetAllUsers");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API GetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
