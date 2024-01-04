package com.bej.authentication.controller;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.security.SecurityTokenImpl;
import com.bej.authentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserServiceImpl userService;
    private SecurityTokenGenerator securityToken;
    private ResponseEntity responseEntity;

    @Autowired

    public UserController(UserServiceImpl userService, SecurityTokenImpl securityToken) {
        this.userService = userService;
        this.securityToken = securityToken;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
//        return new ResponseEntity<>(userService.saveUser (user), HttpStatus.CREATED);
        try {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            // If user already exists,it returns a 500 status with an error message of user already exist
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/logins")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException {
        String token = null;
        try {
            User userData = userService.getUserByEmailAndPassword(user.getEmail(),user.getUserPassword());
            if (user.getEmail().equals(userData.getEmail())) {
                token = securityToken.createToken(user);
                System.out.println("valid token");
            }
            responseEntity = new ResponseEntity<>(token,HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
              throw new InvalidCredentialsException();
//            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUserDetails(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
