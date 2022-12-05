package com.example.UserProductService.repository;

import com.example.UserProductService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    public User findByUserId(int userId);
}