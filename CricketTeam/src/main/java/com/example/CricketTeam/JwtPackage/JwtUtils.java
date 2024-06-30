package com.example.CricketTeam.JwtPackage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {


    private String secreteKey="KzRKcPjvJ8XvAO91jp8NvgYzNSSlXBaDvELkbV4Y86kKM/xbc2emKKJplW7KrN2y\n";

    public String extractPlayername(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object>extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token,UserDetails userDetails)
    {
        final String username=extractPlayername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

    public Date getExpiration(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }


    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build().parseSignedClaims(token).getPayload();

    }

    private Key getSigningKey()
    {
        byte[]keybytes= Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keybytes);
    }
}
