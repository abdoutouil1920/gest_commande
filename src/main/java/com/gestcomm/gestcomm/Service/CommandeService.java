package com.gestcomm.gestcomm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestcomm.gestcomm.Model.Commande;
import com.gestcomm.gestcomm.Model.User;
import com.gestcomm.gestcomm.Repository.CommandeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    // Récupérer toutes les commandes
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Récupérer les commandes d'un client spécifique
    public List<Commande> getCommandesByuser(User user) {
        return commandeRepository.findByuser(user);
    }

    // Récupérer une commande par ID
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    // Créer une nouvelle commande
    public Commande createCommande(Commande commande) {
        commande.setDateCommande(LocalDate.now()); // Ajoute la date actuelle
        commande.setStatut("En attente"); // Statut par défaut
        return commandeRepository.save(commande);
    }

    // Mettre à jour une commande existante
    public Commande updateCommande(Long id, Commande commandeDetails) {
        Commande commande = getCommandeById(id);
        commande.setDescription(commandeDetails.getDescription());
        commande.setMontant(commandeDetails.getMontant());
        commande.setStatut(commandeDetails.getStatut());
        return commandeRepository.save(commande);
    }

    // Supprimer une commande
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
    public String getStatutCommande(Long id) {
        Commande commande = getCommandeById(id); 
        return commande.getStatut();
    }
    public Commande updateStatutCommande(Long id, String nouveauStatut) {
        Commande commande = getCommandeById(id); // Utilise la méthode existante
        commande.setStatut(nouveauStatut);
        return commandeRepository.save(commande);
    }
}
