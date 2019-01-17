package com.simplepos.backend.controllers;

import com.simplepos.backend.models.User;
import com.simplepos.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// default @CrossOrigin allows all
//@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved User";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/findName")
    public @ResponseBody User findUserByName (@RequestParam String name){
        return userRepository.findByName(name);
    }

    @GetMapping("/findId")
    public @ResponseBody User findUserById (@RequestParam String id) {
        return userRepository.findBy_id(new ObjectId(id));
    }
}
