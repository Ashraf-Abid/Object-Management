package net.abid.journalApp.service;

import net.abid.journalApp.repository.UserRepo;
import net.abid.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    @Autowired
    public UserRepo userRepo;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public User saveEntry(User user) {
        return userRepo.save(user);
    }

    public User saveNewUser(User user) {
        user.setRoles(Arrays.asList("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> finaAllEntry() {
        return userRepo.findAll();
    }

    public User findById(ObjectId id) {
        return userRepo.findById(id).orElse(null);
    }

    public boolean deleteById(ObjectId id) {
        userRepo.deleteById(id);
        return true;
    }
    public User findByUserName(String username){
        return userRepo.findByusername(username);
    }

    public User saveAdmin(User user) {
        user.setRoles(Arrays.asList("ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
