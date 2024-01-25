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
}
