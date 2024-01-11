package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/main")
public class MainController {


    @Autowired
    private UserService userService;


    @Autowired
    private GameService gameService;

    @PostMapping(path = "/create/user")
    public User userCreation(@RequestBody User user){
        return userService.userCreation(user);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }

    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }

    @PatchMapping(path="/update")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }


}
