package com.example.estudoSpringWithMongo.resources;

import com.example.estudoSpringWithMongo.domain.Post;
import com.example.estudoSpringWithMongo.domain.User;
import com.example.estudoSpringWithMongo.dto.UserDTO;
import com.example.estudoSpringWithMongo.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
                .map(UserDTO::new)
                .collect(Collectors.toList()); //convertendo de dto para entidade normal
        return ResponseEntity.ok().body(listDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
        User obj = service.fromDTO(userDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
        User obj = service.fromDTO(userDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }

}
