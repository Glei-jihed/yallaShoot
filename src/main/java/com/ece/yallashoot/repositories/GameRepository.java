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



    public void deleteGameById(String id);

    public Game findGameById(String id);

    public List<Game> findGameByCategory(Category category);

    public List<Game> findGameByDate(Date date);

    public List<Game> findGameByFounderId(String id);

    public List<Game> findGameByDateAfter(Date date);

    public List<Game> findGameByPostalCode(int postalCode);

    public List<Game> findGameByCity(String city);

    @Query("SELECT g FROM Game g WHERE g.latitude BETWEEN :minLat AND :maxLat AND g.longitude BETWEEN :minLon AND :maxLon")
    List<Game> findByLatitudeAndLongitudeInterval(@Param("minLat") Double minLat, @Param("maxLat") Double maxLat,
                                                  @Param("minLon") Double minLon, @Param("maxLon") Double maxLon);


















}
