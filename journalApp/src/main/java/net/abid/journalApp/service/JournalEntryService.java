package net.abid.journalApp.service;

import net.abid.journalApp.repository.JournalEntryRepo;
import net.abid.journalApp.entity.JournalEntry;
import net.abid.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    public JournalEntryRepo journalEntryRepo;
    @Autowired
    public UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    @Transactional
    public JournalEntry saveEntry(JournalEntry  journalEntry, String username){
        try {
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDate.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.saveEntry(user);
        return saved;
        } catch (Exception e) {
            throw new RuntimeException("An error occured when saving entry");
        }
    }
    public List<JournalEntry> finaAllEntry(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findbyId(ObjectId id) {
        return journalEntryRepo.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        User user = userService.findByUserName(username);
        boolean removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        //user.getJournalEntryList().remove(id);
        if(removed) {
            userService.saveEntry(user);
            journalEntryRepo.deleteById(id);
        }
        return true;
    }

    public JournalEntry updateById(ObjectId id, JournalEntry journalEntry) {
        JournalEntry entryFindById = journalEntryRepo.findById(id).orElse(null);
        if(entryFindById != null){
            entryFindById.setContent(journalEntry.getContent() != null && !entryFindById.getContent().equals("")? entryFindById.getContent() : journalEntry.getContent());
            entryFindById.setTitle(journalEntry.getTitle() != null && !entryFindById.getTitle().equals("")? entryFindById.getTitle() : journalEntry.getTitle());
        }
        return entryFindById;
    }

    public void updateEntry(JournalEntry entry, String username) {
        journalEntryRepo.save(entry);
    }
    public List<JournalEntry> findByUsername(String username) {
        return journalEntryRepo.findAll();
    }


}
