package com.gestcomm.gestcomm.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestcomm.gestcomm.Model.User;
import com.gestcomm.gestcomm.Repository.UserRepository;
import com.gestcomm.gestcomm.config.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Inscription
    public User signup(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        // Check for missing fields
        if (user.getEmail() == null || user.getMotDePasse() == null || user.getUsername() == null) {
            throw new RuntimeException("Tous les champs doivent être remplis !");
        }
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return userRepository.save(user);
    }
    
    public User createUser(User user) {
        if (user.getEmail() == null || user.getMotDePasse() == null || user.getUsername() == null) {
            throw new RuntimeException("Tous les champs doivent être remplis !");
        }
    
        // Encode the password
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
    
        // Save the user
        User savedUser = userRepository.save(user);
    
        // Generate the token
        String token = JwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole());
        savedUser.setToken(token);
    
        return savedUser;
    }
    
    
    // Connexion
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getMotDePasse())) {
            throw new RuntimeException("Email ou mot de passe incorrect !");
        }
        return user;
    }

    // Récupérer un utilisateur par ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + id));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getAllUsersByRole(String role) {
        return userRepository.findAllByRoleIgnoreCase(role);
    }
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            // Add any other fields that need to be updated
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Method to delete a user
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
    public String generateToken(User user) {
        // Use a JWT library to create the token (e.g., jjwt, jose, etc.)
        // For simplicity, assume you already have a JWT utility class
        return JwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
