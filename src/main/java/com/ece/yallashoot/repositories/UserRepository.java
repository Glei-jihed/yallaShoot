package com.ece.yallashoot.repositories;

import com.ece.yallashoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByEmail(String email);



    //============================== Filtres admine ====================================================================
    List<User> findByFirstName(String firstName);

    List<User> findByConnected(boolean connected);

    List<User> findByLastName(String lastName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User>  findByInscriptionDateIsAfter(Date date);

    List<User> findByAgeBefore(int age);

    List<User> findByAgeAfter(int age);

    List<User> findByAgeBetween(int min, int max);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%")
    List<User> findByFirstNameOrLastNameLike(@Param("keyword") String keyword);






}
