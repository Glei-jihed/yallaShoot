package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;

import java.util.Date;
import java.util.List;

public interface GameService {

    public List<Game> findAllGames();

    public List<Game> findGamesByCategory(Category category);

    public List<Game> findGamesByDate(Date date);

    public List<Game> findGameByDateAfter(Date date);





}
