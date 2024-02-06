package com.ece.yallashoot.controllers;



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
     * @param user the body must contain the user data
     * @return this endpoint will return the data of deleted user
     */
    @DeleteMapping(path="/user/drop")
    public List<User> userDelete(@RequestBody User user){

        return userService.userDelete(user);

    }




    /**
     * @author: Glei jihed
     * the user can use this endpoint to update his data
     * @param user we must send a user data
     * @return User
     */
    @PatchMapping(path="/update")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }


    /**
     * @author: Glei jihed
     * @param user we must give all the data of user
     * this endpoint will make the connected boolean to false and
     */
    @PatchMapping(path="/logout")
    public User logout(@RequestBody User user){
        log.info(user.getId());
        return userService.logoutUser(user);

    }



    /**
     * @author: Glei jihed
     * we use this endpoint to create a new game
     * @param user we will give the user data that contain the games data
     * @return User this endpoint will return an updated user
     */
    @PatchMapping(path = "/create/game")
    public User createGame(@RequestBody User user){

        return userRepository.save(user);
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
     * this endpoint will delete a request
     * @param requestId we must give the id of a request
     */
    @DeleteMapping(path = "delete/request/{requestId}")
    public void deleteRequest(@PathVariable String requestId){
        requestRepository.deleteById(requestId);
    }



    /**
     * @author: Glei jihed
     * we use this endpoint to accept a join request
     * @param requestId we must give the id of the request
     * @return this endpoint will return a request
     */
    @PatchMapping(path = "/accept/request/{requestId}")
    public Request acceptRequest(@PathVariable String requestId){
        Request request = requestRepository.findRequestById(requestId);
        request.setAccepted(true);
        request.setInProgress(false);
        Game game = gameRepository.findGameByJoinRequestsId(requestId);
        game.setRequiredPlayers(game.getRequiredPlayers()+1);
        gameRepository.save(game);
        return requestRepository.save(request);
    }


    /**
     * @author: Glei Jihed
     * we use this endpoint to refuse a request
     * @param requestId we must give the id of the request
     * @return this endpoint will return a request
     */
    @PatchMapping(path = "/refuse/request/{requestId}")
    public Request refuseRequest(@PathVariable String requestId){
        Request request = requestRepository.findRequestById(requestId);
        request.setAccepted(false);
        request.setInProgress(false);
        request.setRefused(true);
        return requestRepository.save(request);
    }



    //==================================         Games list       ==================================================


    /**
     * @author: Glei Jihed
     * we use this endpoint to get the list of all the games saved  in our DB
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


    //====================================== Game filters ==============================================================

    /**
     * @author: Glei Jihed
     * we use this endpoint to get the list of games by the city AND the category
     * @param city we must give a valid city
     * @param category we must give a valid category
     * @return this endpoint will return a list of games that have the city and the category values
     */
    @GetMapping(path = "gamesBy/{city}/{category}")
    public ResponseEntity<List<Game>> findGameByCityAndCategory(@PathVariable String city, @PathVariable Category category){
        List<Game> games = gameService.findGameByCityAndCategory(city,category);
        if (games.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(games, HttpStatusCode.valueOf(200));

    }







    /**
     * @author: Glei Jihed
     * we use this endpoint to get a list of games by category
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
     * @author: glei jihed
     * we use this endpoint to get a list of games by the date
     * @param date we must give a specific date
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
     * we use this endpoint to get the list of games organized after a specific date
     * @param date we must give a date
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
     * @author: Glei Jihed
     * we use this endpoint to delete a game by the id
     * @param id we must give the id
     * @return this endpoint will return the deleted game
     */
    @DeleteMapping(path = "/delete/game/{id}")
    public Game dropGame(@PathVariable String id){
        Optional<Game> game = Optional.ofNullable(gameRepository.findGameById(id));

        if (game.isEmpty()){
            return null;
        }
        gameService.deleteGameById(id);
        return game.get();

    }

    /**
     * @author: Glei jihed
     * we use this endpoint to get the list of games organized by a user
     * @param id we must give an id of an organizer
     * @return this endpoint will return a list of games organized by an organizer
     */
    @GetMapping(path = "/organizer/games/{id}")
    public ResponseEntity<List<Game>> findGameByFounderId(@PathVariable String id){
        List<Game> myGames = gameService.findGameByFounderId(id);
        if (myGames.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(myGames,HttpStatusCode.valueOf(200));
    }

    /**
     * @author: Glei jihed
     * we use this endpoint to get all the games in a specific city
     * @param city we must give the city
     * @return this endpoint wil return a list of games
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
     * we use this end point to get all the request for a specific game
     * @param gameId we must give the id of the game
     * @return this endpoint will return a list of requests
     */
    @GetMapping(path = "/get/request/{gameId}")
    public ResponseEntity<List<Request>> findRequestByRequestedGameId(@PathVariable String gameId){
        List<Request> requests = requestService.findRequestByRequestedGameId(gameId);
        if (requests.isEmpty()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(requests, HttpStatusCode.valueOf(200));
    }


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
