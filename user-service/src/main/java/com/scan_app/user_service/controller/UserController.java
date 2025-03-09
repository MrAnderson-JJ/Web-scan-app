package com.scan_app.user_service.controller;

import com.scan_app.user_service.dto.UserResponse;
import com.scan_app.user_service.dto.UserScanResponse;
import com.scan_app.user_service.model.User;
import com.scan_app.user_service.service.JwtService;
import com.scan_app.user_service.service.ScanService;
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
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ScanService scanService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUserIfDoesntExist(@RequestBody UserResponse userResponse) {
        String userId = jwtService.getUserIdFromToken();

        System.out.println(userId);
        userService.createUserByIdIfDoesntExist(userResponse.userId());
        return "User created successfully";
    }

    @PostMapping("/saveScan")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveScan(@RequestBody UserScanResponse userScanResponse) {
        scanService.saveScan(userScanResponse);
        return "Scan saved successfully";
    }
}
