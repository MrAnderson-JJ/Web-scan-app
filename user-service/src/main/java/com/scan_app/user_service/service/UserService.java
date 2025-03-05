package com.scan_app.user_service.service;

import com.scan_app.user_service.model.User;
import com.scan_app.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUserByIdIfDoesntExist(String userId) {
        if (!userExistsInDb(userId)) {
            userRepository.save(new User(userId));
        }
    }

    public boolean userExistsInDb(String userId) {
        return userRepository.existsById(userId);
    }
}
