package com.gestcomm.gestcomm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gestcomm.gestcomm.Model.User;
import com.gestcomm.gestcomm.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint pour l'inscription
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    // Endpoint pour l'enregistrement
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Endpoint pour la connexion
    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getMotDePasse());
    }

    // Endpoint pour récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // New Endpoint to retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/admins")
public List<User> getAllAdmins() {
    return userService.getAllUsersByRole("admin");
}
@PutMapping("/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    return userService.updateUser(id, updatedUser);
}

// Endpoint pour supprimer un utilisateur
@DeleteMapping("/{id}")
public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
}
}
