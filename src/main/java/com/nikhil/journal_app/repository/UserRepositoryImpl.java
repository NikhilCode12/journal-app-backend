package com.nikhil.journal_app.repository;

import com.nikhil.journal_app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}
