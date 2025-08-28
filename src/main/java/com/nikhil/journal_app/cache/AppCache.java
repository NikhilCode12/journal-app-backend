package com.nikhil.journal_app.cache;

import com.nikhil.journal_app.entity.ConfigJournal;
import com.nikhil.journal_app.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class AppCache {

    public enum keys {
        WEATHER_API
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournal> all = configJournalAppRepository.findAll();
        all.forEach(item -> appCache.put(item.getKey(), item.getValue()));
    }
}
