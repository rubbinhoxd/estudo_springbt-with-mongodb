package com.example.estudoSpringWithMongo.services;

import com.example.estudoSpringWithMongo.domain.User;
import com.example.estudoSpringWithMongo.repository.UserRepository;
import com.example.estudoSpringWithMongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> entityFound = userRepository.findById(id);
        return entityFound.orElseThrow(() -> new ObjectNotFoundException("Objeto não foi encontrado"));
    }

}
