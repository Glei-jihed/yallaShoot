package com.ece.yallashoot.controllers;

import com.ece.yallashoot.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * @author: Glei jihed
     * we use this endpoint to register as a player
     * @param request
     * @return AuthenticationResponse
     */
    @PostMapping(path = "/player/register")
    public ResponseEntity<AuthenticationResponse> playerRegister(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.playerRegister(request));
    }



    /**
     * @author: Glei jihed
     * we use this endpoint to register as an organizer
     * @param request
     * @return AuthenticationResponse
     */
    @PostMapping(path = "/organizer/register")
    public ResponseEntity<AuthenticationResponse> organizerRegister(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.organizerRegister(request));
    }

    @PostMapping(path = "/admin/register")
    public ResponseEntity<AuthenticationResponse> adminRegister(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.adminRegister(request));
    }





    /**
     * @author: Glei jihed
     * we use this endpoint to authenticate user and get a token which will be used to access
     * @param request
     * @return token
     */
    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
