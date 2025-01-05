package com.gestcomm.gestcomm.Model;

public class Rapport {
    private int totalCommandes;
    private int commandesCompletes;
    private int commandesAnnulees;
    private double montantTotal;

    public Rapport(int totalCommandes, int commandesCompletes, int commandesAnnulees, double montantTotal) {
        this.totalCommandes = totalCommandes;
        this.commandesCompletes = commandesCompletes;
        this.commandesAnnulees = commandesAnnulees;
        this.montantTotal = montantTotal;
    }

    // Getters et Setters
    public int getTotalCommandes() {
        return totalCommandes;
    }

    public void setTotalCommandes(int totalCommandes) {
        this.totalCommandes = totalCommandes;
    }

    public int getCommandesCompletes() {
        return commandesCompletes;
    }

    public void setCommandesCompletes(int commandesCompletes) {
        this.commandesCompletes = commandesCompletes;
    }

    public int getCommandesAnnulees() {
        return commandesAnnulees;
    }

    public void setCommandesAnnulees(int commandesAnnulees) {
        this.commandesAnnulees = commandesAnnulees;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
