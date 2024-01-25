package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;

import java.util.List;

public interface GameService {

    public List<Game> findAllGames();

}
