package com.nikhil.journal_app.services;

import com.nikhil.journal_app.entity.JournalEntry;
import com.nikhil.journal_app.entity.User;
import com.nikhil.journal_app.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUsername(userName);
            journalEntry.setDateTime(LocalDateTime.now());
            user.getJournalEntries().add(journalEntryRepository.save(journalEntry));
            userService.saveUser(user);
        } catch (Exception e){
            log.error("Exception: ", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        try{
            journalEntryRepository.save(journalEntry);
        } catch (Exception e){
            log.error("Exception: ", e);
        }
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId entryId){
        return journalEntryRepository.findById(entryId);
    }

    @Transactional
    public boolean deleteById(ObjectId entryId, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(entryId));
            userService.saveUser(user);
            if (removed) {
                journalEntryRepository.deleteById(entryId);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.", e);
        }
        return removed;
    }
}
