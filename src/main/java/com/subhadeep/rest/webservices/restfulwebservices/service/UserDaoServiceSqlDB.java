package com.subhadeep.rest.webservices.restfulwebservices.service;

import com.subhadeep.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.subhadeep.rest.webservices.restfulwebservices.model.User;
import com.subhadeep.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoServiceSqlDB {

    private UserRepository userRepository;

    @Autowired
    public UserDaoServiceSqlDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User with id "+ id +" Doesn't Exist"));
        return user;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        User retrievedUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User with id "+ id +" Doesn't Exist"));
        retrievedUser.setDob(user.getDob());
        retrievedUser.setName(user.getName());
        return userRepository.save(retrievedUser);
    }

    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User with id "+ id +" Doesn't Exist"));
        userRepository.delete(user);
        return user;
    }
}
