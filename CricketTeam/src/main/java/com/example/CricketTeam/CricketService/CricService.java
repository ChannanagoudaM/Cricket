package com.example.CricketTeam.CricketService;

import com.example.CricketTeam.CricketException.PlayerNotFoundException;
import com.example.CricketTeam.CricketPackage.CricRepo;
import com.example.CricketTeam.CricketPackage.Cricket;
import com.example.CricketTeam.CricketTeamApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CricService implements  CrickInfo {

    @Autowired
    CricRepo cricRepo;

    @Override
    public Cricket add(Cricket cricket) {
        Cricket cricket1=cricRepo.save(cricket);
        return cricket1;
    }

    @Override
    public Cricket get(int id) {
        return cricRepo.findById(id).orElseThrow(()->new PlayerNotFoundException("Player not Found with the Id "+id));
    }

    @Override
    public List<Cricket> getAll()
    {
        return cricRepo.findAll();
    }


    public void delete(int id)
    {

        if(!cricRepo.existsById(id))
        {
            throw new PlayerNotFoundException("Player not found with the Id "+id);
        }

        cricRepo.deleteById(id);

    }

    public void deleteAll()
    {
        cricRepo.deleteAll();
    }

}
