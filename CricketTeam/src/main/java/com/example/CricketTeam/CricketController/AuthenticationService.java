package com.example.CricketTeam.CricketController;


import com.example.CricketTeam.CricketException.PlayerNotFoundException;
import com.example.CricketTeam.CricketPackage.CricRepo;
import com.example.CricketTeam.CricketPackage.Cricket;
import com.example.CricketTeam.CricketPackage.Role;
import com.example.CricketTeam.JwtPackage.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CricRepo cricRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

      var user=Cricket.builder()
              .id(request.getId())
              .playername(request.getPlayername())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(Role.USER)
              .build();
                cricRepo.save(user);
       String token=jwtUtils.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getPlayername(),request.getPassword()));

            var user=cricRepo.findByplayername(request.getPlayername()).orElseThrow(()->new PlayerNotFoundException("player not found"));
        String token=jwtUtils.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }
}
