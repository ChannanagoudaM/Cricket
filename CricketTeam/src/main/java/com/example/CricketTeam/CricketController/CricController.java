package com.example.CricketTeam.CricketController;


import com.example.CricketTeam.CricketException.PlayerNotFoundException;
import com.example.CricketTeam.CricketPackage.Cricket;
import com.example.CricketTeam.CricketPackage.Role;
import com.example.CricketTeam.CricketService.CricService;
import com.example.CricketTeam.JwtPackage.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cricket/api")
@RequiredArgsConstructor
public class CricController {



    private final AuthenticationService authenticationService;

    @Autowired
    CricService cricService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
      String playername=request.getPlayername();
      int id=request.getId();
      String password=request.getPassword();
      String email=request.getEmail();
      Cricket cricket=new Cricket(id,playername,email,password, Role.ADMIN);
      cricService.add(cricket);
        return  ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {

        return  ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> get(@PathVariable int id)
    {
       try
       {
           Cricket cricket=cricService.get(id);
           return ResponseEntity.ok(cricket);
       }
       catch(PlayerNotFoundException ex)
       {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
       }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Cricket>> getAll()
    {
        List<Cricket> list=cricService.getAll();
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id)
    {
      try {
          cricService.delete(id);
      }
      catch (PlayerNotFoundException ex)
      {
          throw new PlayerNotFoundException("Player not found with the Id "+id);
      }
        return "User deleted";
    }


    @DeleteMapping("/deleteall")
    public String deleteAll()
    {
        cricService.deleteAll();
        return "All users deleted";
    }







}
