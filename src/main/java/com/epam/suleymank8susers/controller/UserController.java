package com.epam.suleymank8susers.controller;

import com.epam.suleymank8susers.model.User;
import com.epam.suleymank8susers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/users")
    public ResponseEntity<User> save(@RequestBody User user){
        if(user.getUsername() != null && user.getUsername().isBlank()){
            return new ResponseEntity<>( null , HttpStatus.BAD_REQUEST);
        }else{
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser , HttpStatus.OK);
        }
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> get(@PathVariable("id") long id){
        Optional<User> userRecord = userRepository.findById(id);
        if(userRecord.isPresent()){
            return new ResponseEntity<>(userRecord.get() , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        Optional<User> userRecord = userRepository.findById(id);
        if(userRecord.isPresent()){
            userRepository.deleteById(id);
            return  new ResponseEntity<>(null , HttpStatus.OK);
        }else{
            return new ResponseEntity( null , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody User user) {
        if(user.getUsername().isEmpty()){
            return new ResponseEntity(null , HttpStatus.BAD_REQUEST);
        }else{
            Optional<User> userRecord = userRepository.findById(id);
            if(userRecord.isPresent()){
                user.setId(id);
                userRepository.save(user);
                return  new ResponseEntity<>(null , HttpStatus.OK);
            }else{
                return new ResponseEntity( null , HttpStatus.NOT_FOUND);
            }
        }
    }
    @PostMapping(path = "/users/increasePostCount/{id}")
    public ResponseEntity<User> increasePostCount(@PathVariable("id") long id){
       Optional<User> userRecord =  userRepository.findById(id);
       if(userRecord.isPresent()){
           User user = userRecord.get();
           user.increasePostCount();
           userRepository.save(user);
           return new ResponseEntity<>( null , HttpStatus.OK);
       }else {
           return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
       }
    }
    @PostMapping(path = "/users/decreasePostCount/{id}")
    public ResponseEntity<User> decreasePostCount(@PathVariable("id") long id){
        Optional<User> userRecord =  userRepository.findById(id);
        if(userRecord.isPresent()){
            User user = userRecord.get();
            user.decreasePostCount();
            userRepository.save(user);
            return new ResponseEntity<>( null , HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/healthCheck")
    public ResponseEntity healthCheck(){
        return new ResponseEntity(null , HttpStatus.OK);
    }
}
