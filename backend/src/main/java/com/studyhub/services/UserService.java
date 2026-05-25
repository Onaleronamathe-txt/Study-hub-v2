package com.studyhub.services;

import org.springframework.stereotype.Service;
import com.studyhub.models.User;
import com.studyhub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //update user by id
    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(existing -> {
            existing.setUsername(updatedUser.getUsername());
            existing.setEmail(updatedUser.getEmail());
            existing.setBio(updatedUser.getBio());
            existing.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    //delete user by id
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}