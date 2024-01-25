package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.GameRepository;
import com.ece.yallashoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GameServiceImplementation implements GameService{


    @Autowired
    private GameRepository gameRepository;


    @Autowired
    private UserRepository userRepository;







}
