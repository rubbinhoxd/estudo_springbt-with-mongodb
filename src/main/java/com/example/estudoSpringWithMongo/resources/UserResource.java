package com.example.estudoSpringWithMongo.resources;

import com.example.estudoSpringWithMongo.domain.User;
import com.example.estudoSpringWithMongo.dto.UserDTO;
import com.example.estudoSpringWithMongo.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream()
                .map(entity -> new UserDTO(entity))
                .collect(Collectors.toList()); //convertendo de dto para entidade normal
        return ResponseEntity.ok().body(listDTO);
    }

}
