package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.AuthenticationService;
import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Log
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/admin")
public class AdminController {


    @Autowired
    private UserService userService;


    @Autowired
    private GameService gameService;


    @Autowired
    private  AuthenticationService authenticationService;





    /**
     * @author: Glei jihed
     * we use this endpoint to add a new Admin
     * @return AuthenticationResponse
     */
    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> adminRegister(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.adminRegister(request));
    }




    /**
     * @author: Glei Jihed
     * we use this end point to log out
     * @param user we must give an object that contain the user data
     */
    @PatchMapping(path="/logout")
    public User logout(@RequestBody User user){
        log.info(user.getId());
        return userService.logoutUser(user);

    }

    //==================================================================================================================


    /**
     * @author: Glei jihed
     * we can use this function to display  all users in our db
     * @return User
     */
    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll(){

        log.info("liste utisliate");
        return userService.findAll();
    }




    /**
     * @author: Glei Jihed
     * we can use this function to delete a specific user
     * @param user we must give an object that contain the data of a user
     * @return will return a list of users
     */
    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }



    //============================== Admin filters for users ===========================================================

    /**
     * @author: Glei Jihed
     * we can use this endpoint to get all the connected users
     * @return List<User>
     */
    @GetMapping(path="/filter/users/connected")
    public ResponseEntity<List<User>> findUsersConnected(){
        List<User> users = userService.findByConnected(true);
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users,HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




    /**
     * @author: Glei Jihed
     * we use this endpoint to get ths list off adult players
     * @return List<User>
     */
    @GetMapping(path="/users/adults")
    public ResponseEntity<List<User>> findAdultsList(){
        List<User> users = userService.findByAgeAfter(25);
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




    /**
     * @author: Glei Jihed
     * we can use this function get the user or the list of users by the firstname
     * @param firstName we can give a firstname
     * @return List<User>
     */
    @GetMapping(path="/users/byFirstName/{firstName}")
    public ResponseEntity<List<User>> findUserByFirstName(@PathVariable String firstName){
      List<User> users =  userService.findByFirstName(firstName);

      if (users.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users,HttpStatus.OK);
    }







    /**
     * @author: Glei Jihed
     * we use this end point to find the users with last name or last name like a string
     * @param keyword can give any string
     * @return list of users
     */
    @GetMapping(path = "/user/byFirstNameOrLAstNameLike/{keyword}")
    public ResponseEntity<List<User>> findUserByFirstNameOrLastNameLike(@PathVariable String keyword){
        List<User> users = userService.findByFirstNameOrLastNameLike(keyword);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }




    //============================== Admin filters for games ===========================================================


    /**
     * @author: Glei Jihed
     * We use this end point to get the list of all games in the DB
     */
    @GetMapping(path="/games")
    public ResponseEntity<List<Game>> findAllGames(){
        List<Game> games = gameService.findAllGames();

        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<>(games, HttpStatusCode.valueOf(200));

    }


    /**
     * @author: Glei Jihed
     * We use this end point to get the games  by the category
     * @param category we must give a true category
     * @return our function will return a  list of games
     */
    @GetMapping(path="/Games/{category}")
    public ResponseEntity<List<Game>> findGamesByCategory(@PathVariable Category category){
        List<Game> games = gameService.findGamesByCategory(category);
        if(games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }


    /**
     * @author: Glei Jihed
     * we use this end point to get the games by the Date
     * @param date we must give a valid date
     * @return our end point will return a list of games
     */
    @GetMapping(path="/games/date")
    public ResponseEntity<List<Game>> findGamesByDate(@RequestBody Date date){
        List<Game> games = gameService.findGamesByDate(date);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }


    /**
     * @author: Glei Jihed
     * we use this end point to get all the games organized after a specific Date
     * @param date we must give a valid date
     * @return our end point will return a list of games
     */
    @GetMapping(path="/games/afterDate")
    public ResponseEntity<List<Game>> findGamesByDateAfter(@RequestBody Date date){
        List<Game> games = gameService.findGameByDateAfter(date);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }





}
