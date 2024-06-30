package com.example.CricketTeam.CricketService;

import com.example.CricketTeam.CricketPackage.Cricket;
import com.example.CricketTeam.CricketTeamApplication;

import java.util.List;

public interface CrickInfo {


    public Cricket add(Cricket cricket);

    public Cricket get(int id);

    public List<Cricket> getAll();

    public void delete(int id);

    public void deleteAll();

}
