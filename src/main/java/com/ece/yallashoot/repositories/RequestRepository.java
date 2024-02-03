package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RequestRepository extends JpaRepository<Request,String> {

    public Request findRequestById(String id);



}
