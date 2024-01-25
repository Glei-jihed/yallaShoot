package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Category;
import com.ece.yallashoot.entities.Game;
import com.ece.yallashoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GameRepository extends JpaRepository<Game,String> {





    public List<Game> findGameByCategory(Category category);

    public List<Game> findGameByDate(Date date);

    public List<Game> findGameByFounderId(String id);

    public List<Game> findGameByDateAfter(Date date);















}
