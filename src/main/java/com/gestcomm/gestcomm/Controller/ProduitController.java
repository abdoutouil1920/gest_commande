package com.gestcomm.gestcomm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gestcomm.gestcomm.Model.Produit;
import com.gestcomm.gestcomm.Service.ProduitService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Récupérer tous les produits
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    // Ajouter un nouveau produit avec image(s)
    @PostMapping
    public Produit createProduit(@RequestParam("nom") String nom,
                                 @RequestParam("description") String description,
                                 @RequestParam("prix") double prix,
                                 @RequestParam("stock") int stock,
                                 @RequestParam(value = "image", required = false) MultipartFile image,
                                 @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {
        return produitService.createProduit(nom, description, prix, stock, image, images);
    }

    // Mettre à jour un produit existant
    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        return produitService.updateProduit(id, produitDetails);
    }

    // Supprimer un produit
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }
}
