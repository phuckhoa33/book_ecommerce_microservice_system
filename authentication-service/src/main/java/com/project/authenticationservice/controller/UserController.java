package com.project.authenticationservice.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.authenticationservice.model.User;
import com.project.authenticationservice.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("{userid}")
    ResponseEntity<?> getUserById(@PathVariable String userid, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            User data = (User) request.getAttribute("user");

            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("email/{email}")
    ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            User data = userService.getUserByEmail(email);
            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping()
    ResponseEntity<?> updateUser(@RequestBody User user) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.updateUser(user);
            result.put("success", true);
            result.put("message", "Success to call API for bill process");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API for bill process");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
