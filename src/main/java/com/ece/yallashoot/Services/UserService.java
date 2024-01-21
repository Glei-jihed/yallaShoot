package com.ece.yallashoot.Services;


import com.ece.yallashoot.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User userCreation(User user);
    ResponseEntity<List<User>> findAll();

    List<User> userDelete(User user);

    Optional<User> findById(String id);

    User updateUser(User user);




    //===================================== Filtres admin ==============================================================

    List<User> findByConnected(boolean connected);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User>  findByInscriptionDateIsAfter(Date date);

    List<User> findByInscriptionDate(Date date);

    List<User> findByAgeBefore(int age);

    List<User> findByAgeAfter(int age);

    List<User> findByAgeBetween(int min, int max);



}
