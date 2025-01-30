package net.abid.journalApp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
//    private Map<String, JournalEntry> journalEntryMap = new HashMap<>();
//    @GetMapping()
//    public List<JournalEntry> getAll(){
//       return new ArrayList<>(journalEntryMap.values());
//    }
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry journalEntry){
//        journalEntryMap.put(journalEntry.getId(),journalEntry);
//        return true;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public JournalEntry deleteEntry(@PathVariable int id){
//        return   journalEntryMap.remove(id);
//    }
//    @PutMapping("/update/{id}")
//    public boolean updateEntry(@PathVariable String id, @RequestBody JournalEntry journalEntry){
//        journalEntryMap.remove(id);
//        journalEntryMap.put(id, journalEntry);
//        return true;
//    }

}
