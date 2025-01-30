package net.abid.journalApp.controller;

import net.abid.journalApp.entity.User;
import net.abid.journalApp.service.JournalEntryService;
import net.abid.journalApp.service.UserService;
import net.abid.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    JournalEntryService journalEntryService;
    @Autowired
    UserService userService;
    @GetMapping("/get")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> entries = user.getJournalEntryList();
        List<JournalEntry> all = journalEntryService.finaAllEntry();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<JournalEntry >getEntryByID(@PathVariable ObjectId id){
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.findByUserName(username);
//        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
//        if(collect.isEmpty()){
//            Optional<JournalEntry> journalEntry = journalEntryService.findbyId(id);
//            if(journalEntry.isPresent()){
//                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//            }
//        }
//
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @PostMapping("/post")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //User user = userService.findByUserName(username);
        try{
            journalEntryService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(journalEntryService.deleteById(id,username)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(collect != null){
            Optional<JournalEntry> journalEntry1 = journalEntryService.findbyId(id);
            if(journalEntry1.isPresent()){
                JournalEntry old = journalEntry1.get();
                old.setTitle(journalEntry.getTitle()!=null && !journalEntry.getTitle().equals("")? journalEntry.getTitle() : old.getTitle());
                old.setContent(journalEntry.getContent()!=null && !journalEntry.getContent().equals("")? journalEntry.getContent() : old.getContent());
                journalEntryService.updateEntry(old, username);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
            return new ResponseEntity<>(journalEntry1.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalById( @PathVariable ObjectId id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(collect != null){
            Optional<JournalEntry> journalEntry = journalEntryService.findbyId(id);
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       

    }
}
