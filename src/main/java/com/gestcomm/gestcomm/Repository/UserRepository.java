package com.gestcomm.gestcomm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestcomm.gestcomm.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByRoleIgnoreCase(String role);

}
