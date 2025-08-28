package com.nikhil.journal_app.repository;

import com.nikhil.journal_app.entity.ConfigJournal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournal, ObjectId> {
}
