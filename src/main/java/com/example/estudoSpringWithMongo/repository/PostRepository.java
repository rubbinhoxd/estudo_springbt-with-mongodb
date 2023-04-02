package com.example.estudoSpringWithMongo.repository;

import com.example.estudoSpringWithMongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
