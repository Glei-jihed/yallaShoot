package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Request;
import com.ece.yallashoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepository extends JpaRepository<Request,String> {

     Request findRequestById(String id);

     List<Request> findRequestByPlayerId(String id);

     List<Request> findRequestByRequestedGameId(String id);



}
