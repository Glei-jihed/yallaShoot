package com.ece.yallashoot.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author glei-jihed
 * @ this is the response of the authentification request we send user data and we get this token
 */




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationResponse {
    private String token;
}
