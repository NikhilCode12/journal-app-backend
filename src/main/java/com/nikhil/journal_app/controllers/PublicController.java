package com.nikhil.journal_app.controllers;
import com.nikhil.journal_app.entity.User;
import com.nikhil.journal_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
//        if(userService.findByUsername(user.getUserName())!=null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        userService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }
}
