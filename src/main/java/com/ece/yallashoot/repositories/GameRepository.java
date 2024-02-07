package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GameRepository extends JpaRepository<Game,String> {



     void deleteGameById(String id);

     Game findGameById(String id);

     List<Game> findGameByCategory(Category category);

     List<Game> findGameByDate(Date date);

     List<Game> findGameByFounderId(String id);

     Game findGameByJoinRequestsId(String id);

     List<Game> findGameByDateAfter(Date date);

     List<Game> findGameByCityAndCategory(String city,Category category);

     List<Game> findGameByDateAfterAndRequiredPlayersAfter(Date date, int requiredPlayers);

     List<Game> findGameByPostalCode(int postalCode);

     List<Game> findGameByCity(String city);

    @Query("SELECT g FROM Game g WHERE g.latitude BETWEEN :minLat AND :maxLat AND g.longitude BETWEEN :minLon AND :maxLon")
    List<Game> findByLatitudeAndLongitudeInterval(@Param("minLat") Double minLat, @Param("maxLat") Double maxLat,
                                                  @Param("minLon") Double minLon, @Param("maxLon") Double maxLon);


















}
