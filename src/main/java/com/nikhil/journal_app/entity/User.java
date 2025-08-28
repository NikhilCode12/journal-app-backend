package com.nikhil.journal_app.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    @Id
    private ObjectId userId;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    private String email;

    private boolean sentimentAnalysis;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;
}
