package com.giftsubscription.service;

import com.giftsubscription.dto.UserRegisterDTO;
import com.giftsubscription.dto.UserResponseDTO;
import com.giftsubscription.exception.NotFoundException;
import com.giftsubscription.mapper.UserMapper;
import com.giftsubscription.model.SubscriptionType;
import com.giftsubscription.model.User;
import com.giftsubscription.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean register(UserRegisterDTO dto) {
        if (userRepository.existsByMail(dto.getMail()) || userRepository.existsByNumber(dto.getNumber())) {
            return false;
        }

        User user = userMapper.toEntity(dto);
        user.setSubscriptionType(SubscriptionType.STANDARD); // <--- подписка по умолчанию
        userRepository.save(user);
        return true;
    }

    public Optional<UserResponseDTO> login(String mail, String password) {
        return userRepository.findByMail(mail)
                .filter(u -> u.getPassword().equals(password))
                .map(userMapper::toDto);
    }

    public Optional<UserResponseDTO> getUserByMail(String mail) {
        return userRepository.findByMail(mail)
                .map(userMapper::toDto);
    }

    public boolean addBalance(String mail, double amount) {
        if (amount <= 0) return false;

        Optional<User> userOptional = userRepository.findByMail(mail);
        if (userOptional.isEmpty()) return false;

        User user = userOptional.get();
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return true;
    }

    public UserResponseDTO getUserDtoByMail(String mail) {
        User user = userRepository.findByMail(mail)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return userMapper.toDto(user);
    }
}
