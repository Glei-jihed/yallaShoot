package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    /**
     * @author: Glei jihed
     * we can use this function to display the of all users in our db
     * @return User
     */
    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }




    /**
     * @author: Glei jihed
     * we can use this function to find a specific user by his id
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
     * @author: Glei Jihed
     * we can use this function to delete a specific user
     * @param user
     * @return List<User>
     */
    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }



    //============================== Admin filters =====================================================================


    /**
     * @author: Glei Jihed
     * we can use this endpoint to get all the connected users
     * @return List<User>
     */
    @GetMapping(path="/filter/users/connected")
    public ResponseEntity<List<User>> findUsersConnected(){
        List<User> users = userService.findByConnected(true);
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }




    /**
     * @author: Glei Jihed
     * we use this endpoint to get the list off minors players
     * @return List<User>
     */
    @GetMapping(path="/filter/users/minors")
    public ResponseEntity<List<User>> findMinorsList(){
        List<User> users = userService.findByAgeBefore(18);
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(HttpStatus.OK);
    }



    /**
     * @author: Glei Jihed
     * we use this endpoint to get ths list off  players between 18 and 25
     * @return List<User>
     */
    @GetMapping(path="/filter/users/majors")
    public ResponseEntity<List<User>> findTeenList(){
        List<User> users = userService.findByAgeBetween(18,25);
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(HttpStatus.OK);
    }




    /**
     * @author: Glei Jihed
     * we use this endpoint to get ths list off adult players
     * @return List<User>
     */
    @GetMapping(path="/filter/users/adults")
    public ResponseEntity<List<User>> findAdultsList(){
        List<User> users = userService.findByAgeAfter(25);
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(HttpStatus.OK);
    }



}
