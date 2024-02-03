package com.ece.yallashoot.controllers;



import com.ece.yallashoot.Services.GameService;
import com.ece.yallashoot.Services.RequestService;
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
    private RequestService requestService;



    /**
     * @author: Glei jihed
     * we use this endpoint to get a specific user by the id
     * @param id we must give the id
     * @return this endpoint will return the User
     */
    @GetMapping("/user/{id}")
    public Optional<User> findUserById(@PathVariable String id){
        return userService.findById(id);
    }

    /**
     * @author: Glei jihed
     * we can use this endpoint to delete a specific user
     * @param user we must give a specific user
     * @return this endpoint will return the deleted user
     */
    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }


    /**
     * @author: Glei jihed
     * the user can use this endpoint to update his data
     * @param user we must give a user
     * @return User
     */
    @PatchMapping(path="/update")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }




    /**
     * @author: Glei Jihed
     * we use this endpoint logout a user
     * @param user we must give the user
     * @return this endpoint will maker the connected value to false
     */
    @PatchMapping(path="/logout")
    public User logout(@RequestBody User user){
        //log.info(user.getId());
        return userService.logoutUser(user);

    }

    /**
     * @author: Glei Jihed
     * we use this endpoint to sens a join request for a specific game
     * @param gameId we must give the id of game
     * @param playerId we must give the id of the player
     * @param request1 this json must contain the node and the id(auto) of the request
     * @return this endpoint will return a Request
     */
    @PostMapping(path="sendRequest/{gameId}/{playerId}")
    public ResponseEntity<Request> sendRequest(@PathVariable String gameId, @PathVariable String playerId, @RequestBody Request request1){
        Game game = gameService.findGameById(gameId);
        if(game == null){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        Request request = new Request();

        request.setRequestedGame(game);
        Optional<User> user = userService.findById(playerId);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        request.setPlayer(user.get());
        request.setNote(request1.getNote());
        return new ResponseEntity<>(requestRepository.save(request),HttpStatusCode.valueOf(200));
    }

    /**
     * @author: Glei Jihed
     * we use this endpoint to delete a request
     * @param requestId we must send the id
     */
    @DeleteMapping(path = "delete/request/{requestId}")
    public void deleteRequest(@PathVariable String requestId){
        requestRepository.deleteById(requestId);
    }


    //==================================         Game filters       ==================================================
    /**
     * @author: Glei Jihed
     * we use this endpoint to get a specific game by the id
     * @param id we must give the game id
     * @return this end point will return a game or null
     */
    @GetMapping(path = "game/{id}")
    public Game findGameById(@PathVariable String id){
        Optional<Game> game = Optional.ofNullable(gameService.findGameById(id));
        return game.orElse(null);
    }



    /**
     * @author: Glei Jihed
     * we use this endpoint to get all the games saved in the DB
     * @return this endpoint will return a list of games
     */
    @GetMapping(path="/games")
    public ResponseEntity<List<Game>> findAllGames(){
        List<Game> games = gameService.findAllGames();

        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));

    }

    /**
     * @author: Glei Jihed
     * we use this endpoint to get a list of games by the category
     * @param category we must give a valid category
     * @return this endpoint wil return a list of games
     */
    @GetMapping(path="/games/{category}")
    public ResponseEntity<List<Game>> findGamesByCategory(@PathVariable Category category){
        List<Game> games = gameService.findGamesByCategory(category);
        if(games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }


    /**
     * @author: Glei jihed
     * we use this endpoint to find all the games organized at a specific Date
     * @param date we must give a date
     * @return this endpoint will return a list of games
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
     * we use this endpoint to find all the games organized after a specific date
     * @param date we must give a valid date
     * @return this endpoint will return a list of games
     */
    @GetMapping(path="/games/afterDate")
    public ResponseEntity<List<Game>> findGamesByDateAfter(@RequestBody Date date){
        List<Game> games = gameService.findGameByDateAfter(date);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }


    /**
     * @author: Glei jihed
     * we use this endpoint to find all the games in a specific city
     * @param city we must give a city name (string)
     * @return this endpoint will return a list of games
     */
    @GetMapping(path="/games/{city}")
    public ResponseEntity<List<Game>> findGamesByCity(@PathVariable String city){
        List<Game> games = gameService.findGameByCity(city);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }

    /**
     * @author: Glei jihed
     * we use this endpoint to find all the game that have a specific postal code
     * @param postalCode we must give an integer
     * @return this endpoint will return a list of games that have the postal code
     */
    @GetMapping(path="/games/{postalCode}")
    public ResponseEntity<List<Game>> findGamesByPostalCode(@PathVariable int postalCode){
        List<Game> games = gameService.findGameByPostalCode(postalCode);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games,HttpStatusCode.valueOf(200));
    }
//================================== Request get ===================================================================




    /**
     * @author: Glei Jihed
     * we use this end point to get all the requests executed by a specific user
     * @param playerId we must give the id of the player
     * @return this endpoint will return a list of requests
     */
    @GetMapping(path = "/get/request/{playerId}")
    public ResponseEntity<List<Request>> findRequestByPlayerId(@PathVariable String playerId){
        List<Request> requests = requestService.findRequestByPlayerId(playerId);
        if (requests.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(requests, HttpStatusCode.valueOf(200));
    }









}
