package com.gestcomm.gestcomm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gestcomm.gestcomm.Model.Commande;
import com.gestcomm.gestcomm.Model.User;
import com.gestcomm.gestcomm.Service.CommandeService;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Récupérer toutes les commandes
    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // Récupérer les commandes d'un client
    @GetMapping("/user/{userId}")
    public List<Commande> getCommandesByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId); // Simulate a user with this ID
        return commandeService.getCommandesByuser(user);
    }
    
    // Récupérer une commande par ID
    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    // Créer une nouvelle commande
    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    // Mettre à jour une commande
    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        return commandeService.updateCommande(id, commandeDetails);
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }
    @GetMapping("/{id}/statut")
    public String getStatutCommande(@PathVariable Long id) {
    return commandeService.getStatutCommande(id);
    }
    @PutMapping("/{id}/statut")
    public Commande updateStatutCommande(@PathVariable Long id, @RequestBody String nouveauStatut) {
    return commandeService.updateStatutCommande(id, nouveauStatut);
    }
}
