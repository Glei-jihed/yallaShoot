package com.ece.yallashoot.controllers;


import com.ece.yallashoot.Services.AuthenticationService;
import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.RequestService;
import com.ece.yallashoot.Services.UserService;
import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.Request;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;
import com.ece.yallashoot.repositories.RequestRepository;
import com.ece.yallashoot.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins="http://localhost:3000")
@Log
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


    @Autowired
    private RequestRepository requestRepository;




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
        log.info(user.getId());
        return userService.logoutUser(user);

    }


    @PatchMapping(path = "/create/game")
    public User createGame(@RequestBody User user){

        return userRepository.save(user);
    }

    @PostMapping(path="sendRequest/{gameId}/{playerId}")
    public Request sendRequest(@PathVariable String gameId, @PathVariable String playerId, @RequestBody Request request1){
        Game game = gameService.findGameById(gameId);
        Request request = new Request();
        request.setRequestedGame(game);
        Optional<User> user = userService.findById(playerId);
        request.setPlayer(user.get());
        request.setNote(request1.getNote());
        return requestRepository.save(request);
    }

    @DeleteMapping(path = "delete/request/{requestId}")
    public void deleteRequest(@PathVariable String requestId){
        requestRepository.deleteById(requestId);
    }


    @PatchMapping(path = "/accept/request/{requestId}")
    public Request acceptRequest(@PathVariable String requestId){
        Request request = requestRepository.findRequestById(requestId);
        request.setAccepted(true);
        request.setInProgress(false);
        return requestRepository.save(request);
    }

    @PatchMapping(path = "/refuse/request/{requestId}")
    public Request refuseRequest(@PathVariable String requestId){
        Request request = requestRepository.findRequestById(requestId);
        request.setAccepted(false);
        request.setInProgress(false);
        request.setRefused(true);
        return requestRepository.save(request);
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


    @GetMapping(path="/games/{category}")
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
