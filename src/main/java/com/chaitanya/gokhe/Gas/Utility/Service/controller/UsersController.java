package com.chaitanya.gokhe.Gas.Utility.Service.controller;

import com.chaitanya.gokhe.Gas.Utility.Service.model.Users;
import com.chaitanya.gokhe.Gas.Utility.Service.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api")
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/")
    public String greet(){
        return "Welcome to Gas Utility Service";
    }

    @PostMapping("user/login")
    public ResponseEntity<Object> verifyUser(@RequestParam String username, @RequestParam String password){
        return service.verifyUser(username,password);
    }

    @PostMapping("admin/login")
    public ResponseEntity<Object> verifyAdmin(@RequestParam String username, @RequestParam String password){
        return service.verifyAdmin(username,password);
    }

    @PostMapping("user/register")
    public ResponseEntity<Object> registerUser(@RequestBody Users user){
        try {
            return service.registerUser(user);
        } catch (DataIntegrityViolationException ex) { // Example: Unique constraint violation
            return new ResponseEntity<>("User already exists or data integrity violation.", HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) { // Example: Invalid arguments passed
            return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) { // Catch-all for unexpected exceptions
            return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("admin/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody Users user){
        try {
            return service.registerAdmin(user);
        } catch (DataIntegrityViolationException ex) { // Example: Unique constraint violation
            return new ResponseEntity<>("Admin already exists or data integrity violation.", HttpStatus.CONFLICT);
        } catch (IllegalArgumentException ex) { // Example: Invalid arguments passed
            return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) { // Catch-all for unexpected exceptions
            return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
