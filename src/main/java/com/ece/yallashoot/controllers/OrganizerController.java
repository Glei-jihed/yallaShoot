package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.AuthenticationService;
import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.RequestService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;
import com.ece.yallashoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/organizer")
public class OrganizerController {


    @Autowired
    public GameService gameService;

    @Autowired
    public RequestService requestService;

    @Autowired
    public UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;




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



    //==================================         Games list       ==================================================

    @GetMapping(path="/games")
    public ResponseEntity<List<Game>> findAllGames(){
        List<Game> games = gameService.findAllGames();

        if (games.isEmpty()){
            return new ResponseEntity<List<Game>>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));

    }


    //====================================== Game filters ==============================================================


    @GetMapping(path="/Games/{category}")
    public ResponseEntity<List<Game>> findGamesByCategory(@PathVariable Category category){
        List<Game> games = gameService.findGamesByCategory(category);
        if(games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));
    }

    @GetMapping(path="/games/date")
    public ResponseEntity<List<Game>> findGamesByDate(@RequestBody Date date){
        List<Game> games = gameService.findGamesByDate(date);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));
    }


    @GetMapping(path="/games/afterDate")
    public ResponseEntity<List<Game>> findGamesByDateAfter(@RequestBody Date date){
        List<Game> games = gameService.findGameByDateAfter(date);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));
    }


    @PatchMapping(path = "/create/game")
    public User createGame(@RequestBody User user){
        Optional<User> user1 = userService.findById(user.getId());
        if (user1.isEmpty()){
            return null;
        }
        return userRepository.save(user);
    }


    @DeleteMapping(path = "/delete/game/{id}")
    public Game dropGame(@PathVariable String id){
        Optional<Game> game = Optional.ofNullable(gameRepository.findGameById(id));

        if (game.isEmpty()){
            return null;
        }
        gameService.deleteGameById(id);
        return game.get();

    }


    @GetMapping(path = "/organizer/games/{id}")
    public ResponseEntity<List<Game>> findGameByFounderId(@PathVariable String id){
        List<Game> myGames = gameService.findGameByFounderId(id);
        if (myGames.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(myGames,HttpStatusCode.valueOf(200));
    }


    @GetMapping(path="/games/{city}")
    public ResponseEntity<List<Game>> findGamesByCity(@PathVariable String city){
        List<Game> games = gameService.findGameByCity(city);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));
    }


    @GetMapping(path="/games/{postalCode}")
    public ResponseEntity<List<Game>> findGamesByPostalCode(@PathVariable int postalCode){
        List<Game> games = gameService.findGameByPostalCode(postalCode);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<List<Game>>(games,HttpStatusCode.valueOf(200));
    }


}
