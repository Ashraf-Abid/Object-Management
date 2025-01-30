package net.abid.journalApp.controller;

import net.abid.journalApp.entity.User;
import net.abid.journalApp.repository.UserRepo;
import net.abid.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser(){
        List<User> users = userService.finaAllEntry();
        if(users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);

        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        User admin = userService.saveAdmin(user);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
}
