package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.AuthenticationService;
import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.Request;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/player")
public class PlayerController {


    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RequestRepository requestRepository;


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


    @GetMapping(path="/Games/tunis/{category}")
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


    @PostMapping(path="/send/request/{idGame}/{idPlayer}")
    public ResponseEntity<Request> sendRequest(@PathVariable String idGame,String idPlayer,@RequestBody Request request){
        Game game = gameService.findGameById(idGame);
        if (game == null){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        Optional<User> user = userService.findById(game.getFounder().getId());
        if ( user.isEmpty() ){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

        Optional<User> player = userService.findById(idPlayer);
        request.setPlayer(player.get());
        request.setRequestedGame(game);
        return new ResponseEntity<Request>(requestRepository.save(request),HttpStatusCode.valueOf(200));
    }






}
