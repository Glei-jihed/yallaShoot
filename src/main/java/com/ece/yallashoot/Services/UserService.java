package com.ece.yallashoot.Services;


import com.ece.yallashoot.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface UserService {


    public User userCreation(User user);
    public ResponseEntity<List<User>> findAll();

    public List<User> userDelete(User user);

    public ResponseEntity<User> findUserById(User user);

    public User updateUser(User user);


    //===================================== Filtres admin ==============================================================

    public List<User> findByConnected(boolean connected);

    public List<User> findByLastName(String lastName);

    public List<User> findByFirstName(String firstName);

    public List<User> findByFirstNameAndLastName(String firstName, String lastName);

    public List<User> findByEmail(String email);

    public List<User>  findByInscriptionDateIsAfter(Date date);

    public List<User> findByInscriptionDate(Date date);


}
