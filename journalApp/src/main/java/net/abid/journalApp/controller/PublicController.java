package net.abid.journalApp.controller;

import net.abid.journalApp.entity.User;
import net.abid.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userService.saveNewUser(user);
    }
    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }
}
