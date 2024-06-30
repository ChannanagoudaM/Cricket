package com.example.CricketTeam.CricketController;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    private int id;
    private String playername;
    private  String email;
    private String password;
}
