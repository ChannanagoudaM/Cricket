package com.example.CricketTeam.CricketException;

public class PlayerNotFoundException extends  RuntimeException{


    public PlayerNotFoundException(String message)
    {
        super(message);
    }
}
