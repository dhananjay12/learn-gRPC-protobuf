package com.mynotes.grpc.demo.jsonapi.controller;


import com.mynotes.grpc.demo.jsonapi.controller.mapper.AuthorMapper;
import com.mynotes.grpc.demo.jsonapi.controller.mapper.PostMapper;
import com.mynotes.grpc.demo.jsonapi.dto.PostDTO;
import com.mynotes.grpc.demo.jsonapi.persistence.AuthorEntity;
import com.mynotes.grpc.demo.jsonapi.persistence.AuthorsRepository;
import com.mynotes.grpc.demo.jsonapi.persistence.PostsEntity;
import com.mynotes.grpc.demo.jsonapi.persistence.PostsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    AuthorsRepository authorsRepository;

    @Autowired
    PostsRepository postsRepository;

    @GetMapping("/author/{id}")
    public ResponseEntity<?> findAuthor(@PathVariable int id) {

        Optional<AuthorEntity> result = authorsRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(AuthorMapper.makeDTO(result.get()));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/posts/{limit}")
    public List<PostDTO> findPosts(@PathVariable int limit) {

        List<PostsEntity> posts = postsRepository.findAllWithLimit(limit);
        List<PostDTO> result = new ArrayList<>();

        for (PostsEntity postsEntity : posts) {
            result.add(PostMapper.makeDTO(postsEntity));
        }

        return result;

    }

}
