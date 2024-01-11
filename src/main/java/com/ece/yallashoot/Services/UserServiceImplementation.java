package com.ece.yallashoot.Services;


import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public User userCreation(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);

    }

    @Override
    public List<User> userDelete(User user) {

        userRepository.deleteById(user.getId());

        return userRepository.findAll();

    }

    @Override
    public ResponseEntity<User> findUserById(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(HttpStatus.OK);
    }


    @Override
    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isEmpty())
        {
            return null;

        } else {
            return userRepository.save(user);
        }
    }



    //=============== Filtres Admin ====================================================================================


    @Override
    public List<User> findByConnected(boolean connected) {

        return userRepository.findByConnected(connected);

    }

    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<User> findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    @Override
    public List<User> findByInscriptionDateIsAfter(Date date) {

        return userRepository.findByInscriptionDateIsAfter(date);

    }

    @Override
    public List<User> findByInscriptionDate(Date date) {

        return userRepository.findByInscriptionDate(date);

    }

}
