package com.example;

import org.springframework.web.bind.annotation.*;
import java.util.List;

// Говорим Spring, что это REST контроллер (Лаба 5)
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*") // Разрешаем доступ с любого адреса (Лаба 6)
public class PostController {

    private final PostRepository postRepository;

    // Внедряем репозиторий через конструктор (Лаба 7)
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Метод для получения всех сообщений мессенджера
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Метод для публикации нового сообщения
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }
}