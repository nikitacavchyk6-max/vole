package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Этот интерфейс сам создаст методы для сохранения и поиска
}