package com.ece.yallashoot.Services;


import com.ece.yallashoot.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {



    ResponseEntity<List<User>> findAll();

    List<User> userDelete(User user);

    Optional<User> findById(String id);

    User updateUser(User user);

    User logoutUser(User user);




    //===================================== Filtres admin ==============================================================

    List<User> findByConnected(boolean connected);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User>  findByInscriptionDateIsAfter(Date date);



    List<User> findByAgeBefore(int age);

    List<User> findByAgeAfter(int age);

    List<User> findByAgeBetween(int min, int max);

    List<User> findByFirstNameOrLastNameLike(String keyword);



}
