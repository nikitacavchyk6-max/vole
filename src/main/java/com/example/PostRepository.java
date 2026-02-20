package com.example.socialnetwork.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {
    // Временный список постов (пока без внешней БД, но работает как лента)
    private final List<Map<String, String>> posts = new ArrayList<>();

    @GetMapping
    public List<Map<String, String>> getPosts() {
        return posts;
    }

    @PostMapping
    public Map<String, String> addPost(@RequestParam String author, @RequestParam String content) {
        Map<String, String> post = new HashMap<>();
        post.put("author", author);
        post.put("content", content);
        post.put("time", LocalDateTime.now().toString());
        posts.add(0, post); // Новые посты будут сверху
        return post;
    }
}