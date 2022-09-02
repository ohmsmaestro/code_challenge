package com.api.coding_challenge.controller;

import com.api.coding_challenge.domain.request.UserRequest;
import com.api.coding_challenge.domain.response.UserResponse;
import com.api.coding_challenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(request));
    }

    @PutMapping(value = "/{emailId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUser(@PathVariable(value = "emailId") String emailId, @RequestBody @Valid UserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(emailId, request));
    }

    @GetMapping(value = "/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "emailId") String emailId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(emailId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }

    @DeleteMapping(value = "/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> deleteUser(@PathVariable(value = "emailId") String emailId){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(emailId));
    }
}
