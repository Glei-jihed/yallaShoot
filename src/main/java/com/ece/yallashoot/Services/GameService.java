package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAllGames();

    Game findGameById(String id);

    List<Game> findGameByCityAndCategory(String city, Category category);

    List<Game> findGamesByCategory(Category category);

    List<Game> findGamesByDate(Date date);

    List<Game> findGameByDateAfterAndRequiredPlayersAfter(Date date, int requiredPlayers);

    List<Game> findGameByDateAfter(Date date);

    List<Game> findByLatitudeAndLongitudeInterval(@Param("minLat") Double minLat, @Param("maxLat") Double maxLat,
                                                  @Param("minLon") Double minLon, @Param("maxLon") Double maxLon);


    List<Game> findGameByPostalCode(int postalCode);
    List<Game> findGameByCity(String city);
    List<Game> findGameByFounderId(String id);

    void deleteGameById(String id);




}
