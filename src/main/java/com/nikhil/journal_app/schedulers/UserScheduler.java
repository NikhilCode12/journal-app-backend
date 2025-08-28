package com.nikhil.journal_app.schedulers;

import com.nikhil.journal_app.cache.AppCache;
import com.nikhil.journal_app.entity.JournalEntry;
import com.nikhil.journal_app.entity.User;
import com.nikhil.journal_app.enums.Sentiment;
import com.nikhil.journal_app.repository.UserRepositoryImpl;
import com.nikhil.journal_app.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail(){
        List<User> users = userRepository.getUsersForSA();
        for(User user : users){
            List<Sentiment> sentiments = user.getJournalEntries().stream()
                    .filter(entry -> entry.getDateTime().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment)
                    .toList();
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if(sentiment!=null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for 7 days", mostFrequentSentiment.toString());
            }
        }
    }
}
