package com.gestcomm.gestcomm.Repository;

import com.gestcomm.gestcomm.Model.Commande;
import com.gestcomm.gestcomm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByuser(User user); 
}