package com.bej.authentication.service;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException;

    User getUserByEmailAndPassword(String email, String userPassword) throws InvalidCredentialsException;

    List getUserDetails();
}
