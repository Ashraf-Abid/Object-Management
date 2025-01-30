package net.abid.journalApp.controller;

import net.abid.journalApp.apiResponse.WeatherResponse;
import net.abid.journalApp.entity.User;
import net.abid.journalApp.repository.UserRepo;
import net.abid.journalApp.service.UserService;
import net.abid.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WeatherService weatherService;
//    @GetMapping("/get")
//    public List<User> getAllUser(){
//        return userService.finaAllEntry();
//    }
//    @GetMapping("/get/{id}")
//    public User getUserById(@PathVariable ObjectId id){
//        return userService.findById(id);
//    }


    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = userService.findByUserName(username);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        userService.saveEntry(user1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/hello")
    public String greeting(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        WeatherResponse respone = weatherService.getWeather("Dhaka");
        String greeting = "";
        if(respone != null) {
            greeting +=  " weather feels like " + respone.getCurrent().getFeelslike();
        }
        return "Hello " + userName + " "  + greeting;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepo.findByusername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
