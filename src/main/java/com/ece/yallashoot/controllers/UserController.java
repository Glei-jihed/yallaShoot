package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.AuthenticationService;
import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/player")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;


    @Autowired
    private AuthenticationService authenticationService;



    @GetMapping("/user/{id}")
    public Optional<User> findUserById(@PathVariable String id){
        return userService.findById(id);
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


    @PatchMapping(path="/logout")
    public User logout(@RequestBody User user){
        //log.info(user.getId());
        return userService.logoutUser(user);

    }



    //==================================         Game functions       ==================================================

    @GetMapping(path="/games")
    public ResponseEntity<List<Game>> findAllGames(){
        List<Game> games = gameService.findAllGames();

        if (games.isEmpty()){
            return new ResponseEntity<List<Game>>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));

    }





}
