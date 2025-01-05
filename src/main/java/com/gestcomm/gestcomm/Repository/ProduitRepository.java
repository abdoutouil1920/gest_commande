package com.gestcomm.gestcomm.Repository;

import com.gestcomm.gestcomm.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
