package com.api.coding_challenge.service.impl;

import com.api.coding_challenge.domain.request.UserRequest;
import com.api.coding_challenge.domain.response.UserResponse;
import com.api.coding_challenge.exception.ApiException;
import com.api.coding_challenge.model.User;
import com.api.coding_challenge.repository.UserRepository;
import com.api.coding_challenge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse saveUser(UserRequest request) {

            User user = mapper.map(request, User.class);
            Optional<User> userOptional = userRepository.findById(user.getEmail());
            if(userOptional.isPresent()){
                throw new ApiException(HttpStatus.CONFLICT, "User with same email exist");
            }
            return mapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse deleteUser(String emailId) {
        Optional<User> userOptional = userRepository.findById(emailId);
        if(!userOptional.isPresent()){
            throw new ApiException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        User user = userOptional.get();
        userRepository.delete(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(String emailId, UserRequest request) {
        Optional<User> userOptional = userRepository.findById(emailId);
        if(!userOptional.isPresent()){
            throw new ApiException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        User existingUser = userOptional.get();
        User userForUpdate = mapper.map(request, User.class);
        Optional<User> userExistByEmailOptional = userRepository.findById(userForUpdate.getEmail());

        if(userExistByEmailOptional.isPresent()){
            User checkedUserByEmail = userExistByEmailOptional.get();
            if(existingUser.getEmail()!=checkedUserByEmail.getEmail()){
                throw new ApiException(HttpStatus.NOT_FOUND, "User with email already exist");
            }

            return mapper.map(userRepository.save(userForUpdate), UserResponse.class);
        }

        return mapper.map(userRepository.save(userForUpdate), UserResponse.class);
    }

    @Override
    public UserResponse getUserById(String emailId) {
        Optional<User> userOptional = userRepository.findById(emailId);
        if(!userOptional.isPresent()){
            throw new ApiException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        User user = userOptional.get();
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user-> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
    }
}
