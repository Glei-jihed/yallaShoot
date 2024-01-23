package com.ece.yallashoot.controllers;

import com.ece.yallashoot.entities.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterRequest {


    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private String phone;
    private byte[] profilePicture;




}