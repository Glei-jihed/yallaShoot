package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;



@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
}
