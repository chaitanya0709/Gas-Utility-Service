package com.chaitanya.gokhe.Gas.Utility.Service.service;

import com.chaitanya.gokhe.Gas.Utility.Service.model.Users;
import com.chaitanya.gokhe.Gas.Utility.Service.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    JWTService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private String getRole(Authentication authentication){
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("NO_ROLE");
    }

    public ResponseEntity<Object> verifyUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        String role = getRole(authentication);

        if(authentication.isAuthenticated() && role.equals("ROLE_USER")){
            return new ResponseEntity<>(jwtService.generateToken(username, role), HttpStatus.OK);
        }
        return new ResponseEntity<>("Admin login not allowed.",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Object> verifyAdmin(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        String role = getRole(authentication);

        if(authentication.isAuthenticated() && role.equals("ROLE_ADMIN")){
            return new ResponseEntity<>(jwtService.generateToken(username, role), HttpStatus.OK);
        }
        return new ResponseEntity<>("User login not allowed.",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Object> registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        usersRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> registerAdmin(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");
        usersRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
