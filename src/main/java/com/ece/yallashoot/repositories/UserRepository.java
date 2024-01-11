package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //============================== Filtres admine ==============
    public List<User> findByFirstName(String firstName);

    public List<User> findByConnected(boolean connected);

    public List<User> findByLastName(String lastName);

    public List<User> findByFirstNameAndLastName(String firstName, String lastName);

    public List<User> findByEmail(String email);

    public List<User>  findByInscriptionDateIsAfter(Date date);

    public List<User> findByInscriptionDate(Date date);




}
