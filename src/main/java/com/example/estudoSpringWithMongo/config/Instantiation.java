package com.example.estudoSpringWithMongo.config;

import com.example.estudoSpringWithMongo.domain.Post;
import com.example.estudoSpringWithMongo.domain.User;
import com.example.estudoSpringWithMongo.dto.AuthorDTO;
import com.example.estudoSpringWithMongo.repository.PostRepository;
import com.example.estudoSpringWithMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post postOne = new Post(null, sdf.parse("21/03/2022"), "Partiu Viagem!", "Vou viajar pra Acopiara!",new AuthorDTO(maria));
        Post postTwo = new Post(null, sdf.parse("23/03/2022"), "Bom dia", "Hoje eu tô pro crime", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(postOne, postTwo));

        maria.getPosts().addAll(Arrays.asList(postOne, postTwo));

        userRepository.save(maria);
    }
}
