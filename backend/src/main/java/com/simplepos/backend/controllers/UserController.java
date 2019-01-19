package com.simplepos.backend.controllers;

import com.simplepos.backend.models.User;
import com.simplepos.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/findUser")
    public @ResponseBody
    User findUserByName(@RequestParam String username) {
        // TODO: 1/18/19 if not user is found
        return userRepository.findByUsername(username).get();
    }
}
