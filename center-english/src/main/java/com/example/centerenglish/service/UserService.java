package com.example.centerenglish.service;

import com.example.centerenglish.request.UserRequest;
import com.example.centerenglish.request.UserRequestFilter;
import com.example.centerenglish.response.UserResponse;

import java.util.Map;

public interface UserService {
    UserResponse createUserWithSchedule(UserRequest request);
    Map<String, Object> getAllUsers(UserRequestFilter userRequestFilter);
    UserResponse getUserById(Long userId);
    Boolean updateUser(Map<String, Object> req);
    Boolean deleteRequestUserById(Long id);
}
