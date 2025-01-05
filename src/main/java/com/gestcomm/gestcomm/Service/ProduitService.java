package com.gestcomm.gestcomm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestcomm.gestcomm.Model.Produit;
import com.gestcomm.gestcomm.Repository.ProduitRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;

    // Récupérer tous les produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Récupérer un produit par ID
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));
    }

    // Ajouter un nouveau produit avec image(s)
    public Produit createProduit(String nom, String description, double prix, int stock, MultipartFile image, List<MultipartFile> images) throws IOException {
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setStock(stock);
        produit.setDate_created(LocalDateTime.now()); // Set date created to current time

        // Handle single image upload
        if (image != null && !image.isEmpty()) {
            produit.setImage(image.getBytes());
        }

        // Handle multiple images upload
        if (images != null && !images.isEmpty()) {
            List<byte[]> imagesBytes = images.stream()
                .map(file -> {
                    try {
                        return file.getBytes();
                    } catch (IOException e) {
                        throw new RuntimeException("Error uploading images", e);
                    }
                }).toList();
            produit.setImages(imagesBytes);
        }

        return produitRepository.save(produit);
    }

    // Mettre à jour un produit existant
    public Produit updateProduit(Long id, Produit produitDetails) {
        Produit produit = getProduitById(id);
        produit.setNom(produitDetails.getNom());
        produit.setDescription(produitDetails.getDescription());
        produit.setPrix(produitDetails.getPrix());
        produit.setStock(produitDetails.getStock());
        return produitRepository.save(produit);
    }

    // Supprimer un produit
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
