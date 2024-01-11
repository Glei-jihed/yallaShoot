package com.ece.yallashoot.Services;

import com.ece.yallashoot.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameServiceImplementation implements GameService{


    @Autowired
    private GameRepository gameRepository;
}
