package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/main")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;


    /**
     * @author: Glei jihed
     * we can use this endpoint to find a specific user by the id
     * @param id
     * @return User
     */
    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id){
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(HttpStatus.OK);
    }


    /**
     * @author: Glei jihed
     * we can use this endpoint to delete a specific user
     * @param user
     * @return List<User>
     */
    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }



    /**
     * @author: Glei jihed
     * the user can use this endpoint to update his data
     * @param user
     * @return User
     */
    @PatchMapping(path="/update")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }








}
