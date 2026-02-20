package com.example;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Лаба 6: Разрешаем фронтенду доступ
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> data) {
        String user = data.get("username");
        String pass = data.get("password");

        if (userRepository.findByUsername(user).isPresent()) return "Ошибка: Юзер есть!";

        User newUser = new User();
        newUser.setUsername(user);
        newUser.setPassword(pass);
        userRepository.save(newUser);
        return "Успех!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> data) {
        return userRepository.findByUsername(data.get("username"))
                .filter(u -> u.getPassword().equals(data.get("password")))
                .map(u -> "Добро пожаловать!")
                .orElse("Ошибка!");
    }
}
