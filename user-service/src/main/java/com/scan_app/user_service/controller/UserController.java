package com.scan_app.user_service.controller;

import com.scan_app.user_service.dto.UserResponse;
import com.scan_app.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    //Save user if doesn't exist
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUserIfDoesntExist(@RequestBody UserResponse userResponse) {
        userService.createUserByIdIfDoesntExist(userResponse.userId());
        return "User created successfully";
    }
}
