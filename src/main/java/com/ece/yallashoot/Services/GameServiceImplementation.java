package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;
import com.ece.yallashoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class GameServiceImplementation implements GameService{


    @Autowired
    private GameRepository gameRepository;


    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> findGamesByCategory(Category category) {
        return gameRepository.findGameByCategory(category);
    }

    @Override
    public List<Game> findGamesByDate(Date date) {
        return gameRepository.findGameByDate(date);
    }

    @Override
    public List<Game> findGameByDateAfter(Date date) {
        return gameRepository.findGameByDateAfter(date);
    }

    @Override
    public List<Game> findByLatitudeAndLongitudeInterval(Double minLat, Double maxLat, Double minLon, Double maxLon) {
        return gameRepository.findByLatitudeAndLongitudeInterval(minLat,maxLat,minLon,maxLon);
    }

    @Override
    public List<Game> findGameByPostalCode(int postalCode) {
        return gameRepository.findGameByPostalCode(postalCode);
    }

    @Override
    public List<Game> findGameByCity(String city) {
        return gameRepository.findGameByCity(city);
    }

    @Override
    public List<Game> findGameByFounderId(String id) {
        return gameRepository.findGameByFounderId(id);
    }

    @Override
    public void deleteGameById(String id) {

    }
}
