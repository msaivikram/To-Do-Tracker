package com.bej.authentication.service;

import com.bej.authentication.domain.User;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> optUser=userRepository.findById(user.getEmail());
        if(optUser.isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String userPassword) throws InvalidCredentialsException {
        User details = userRepository.findByEmailAndUserPassword (email, userPassword);
        if (details == null) {
            throw new InvalidCredentialsException ();
        }
            return details;

    }

    @Override
    public List<User> getUserDetails() {
        return userRepository.findAll();
    }
}
