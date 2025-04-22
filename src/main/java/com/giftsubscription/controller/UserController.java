package com.giftsubscription.controller;

import com.giftsubscription.dto.UserLoginDTO;
import com.giftsubscription.dto.UserRegisterDTO;
import com.giftsubscription.dto.UserResponseDTO;
import com.giftsubscription.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegisterDTO userDTO) {
        boolean success = userService.register(userDTO);
        if (success) {
            return ResponseEntity.ok("Пользователь зарегистрирован успешно.");
        } else {
            return ResponseEntity.badRequest().body("Почта или номер уже используются.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO dto) {
        Optional<UserResponseDTO> user = userService.login(dto.getMail(), dto.getPassword());
        return user.map(u -> ResponseEntity.ok("Добро пожаловать, " + u.getName()))
                .orElseGet(() -> ResponseEntity.status(401).body("Неверная почта или пароль."));
    }

    @GetMapping("/{mail}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String mail) {
        return userService.getUserByMail(mail)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add-balance")
    public ResponseEntity<String> addBalance(@RequestParam String mail,
                                             @RequestParam double amount) {
        boolean success = userService.addBalance(mail, amount);
        if (success) {
            return ResponseEntity.ok("Баланс успешно пополнен.");
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден или сумма некорректна.");
        }
    }
}
