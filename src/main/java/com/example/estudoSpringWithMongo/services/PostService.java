package com.example.estudoSpringWithMongo.services;

import com.example.estudoSpringWithMongo.domain.Post;
import com.example.estudoSpringWithMongo.domain.User;
import com.example.estudoSpringWithMongo.dto.UserDTO;
import com.example.estudoSpringWithMongo.repository.PostRepository;
import com.example.estudoSpringWithMongo.repository.UserRepository;
import com.example.estudoSpringWithMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> entityFound = postRepository.findById(id);
        return entityFound.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado"));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

}
