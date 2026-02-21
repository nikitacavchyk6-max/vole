package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@CrossOrigin
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired private UserRepo userRepo;
    @Autowired private PostRepo postRepo;

    // --- РЕГИСТРАЦИЯ И ВХОД ---
    @PostMapping("/register")
    public User register(@RequestBody User user) { return userRepo.save(user); }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("Неверная почта или пароль"));
    }

    // --- ЛЕНТА ПОСТОВ ---
    @GetMapping("/posts")
    public List<Post> getPosts() { return postRepo.findAll(); }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) { return postRepo.save(post); }
}

// РЕПОЗИТОРИИ (Интерфейсы для БД)
@Repository interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
@Repository interface PostRepo extends JpaRepository<Post, Long> {}