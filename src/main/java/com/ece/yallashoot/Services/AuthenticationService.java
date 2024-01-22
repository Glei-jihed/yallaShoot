package com.ece.yallashoot.Services;

import com.ece.yallashoot.controllers.AuthenticationRequest;
import com.ece.yallashoot.controllers.AuthenticationResponse;
import com.ece.yallashoot.controllers.RegisterRequest;
import com.ece.yallashoot.entities.Role;
import com.ece.yallashoot.entities.User;
import com.ece.yallashoot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse playerRegister(RegisterRequest request){
        var user = User.builder()
                .id(request.getId())
                .age(request.getAge())
                .phone(request.getPhone())
                .profilePicture(request.getProfilePicture())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PLAYER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse organizerRegister(RegisterRequest request){
        var user = User.builder()
                .id(request.getId())
                .age(request.getAge())
                .phone(request.getPhone())
                .profilePicture(request.getProfilePicture())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        user.setConnected(true);
        repository.save(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}