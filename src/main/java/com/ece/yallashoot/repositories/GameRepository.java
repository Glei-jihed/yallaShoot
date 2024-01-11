package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
