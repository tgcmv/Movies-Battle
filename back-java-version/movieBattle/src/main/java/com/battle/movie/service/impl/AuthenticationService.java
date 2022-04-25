package com.battle.movie.service.impl;

import com.battle.movie.model.User;
import com.battle.movie.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Value("${moviebattle.jwt.expiration}")
    private String expiration;

    @Value("${moviebattle.jwt.secret}")
    private String secret;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByName(username);

        if (user.isPresent()) {
            return user.get();
        }

        //TODO mensagem
        throw new UsernameNotFoundException("Invalid user!");
    }

    public String generateToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();

        Date date = new Date();

        return Jwts.builder()
                .setIssuer("API movie battle")
                .setSubject(user.getId())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public User getUserFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return repository.findById(claims.getSubject()).get();
    }
}
