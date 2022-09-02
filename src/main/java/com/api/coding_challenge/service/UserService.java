package com.api.coding_challenge.service;

import com.api.coding_challenge.domain.request.UserRequest;
import com.api.coding_challenge.domain.response.UserResponse;
import com.api.coding_challenge.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest request);
    UserResponse deleteUser(String emailId);
    UserResponse updateUser(String emailId, UserRequest request);
    UserResponse getUserById(String emailId);
    List<UserResponse> getAllUsers();
}
