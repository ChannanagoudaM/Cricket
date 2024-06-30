package com.example.CricketTeam.CricketPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CricRepo extends JpaRepository<Cricket,Integer> {

    Optional<Cricket> findByplayername(String playername);
}
