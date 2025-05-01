package com.shilov.sobes_bot.service;

import com.shilov.sobes_bot.model.User;
import com.shilov.sobes_bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User getUserByIdOrCreateUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            User newUser = new User();
            newUser.setId(id);
            return userRepository.save(newUser);
        }
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
