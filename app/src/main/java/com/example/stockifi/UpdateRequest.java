package com.example.stockifi;

import java.util.Date;

public class UpdateRequest {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String sexe;
    private String taille;
    private String poids;
    private String dateDeNaissance;
    private String régimeSpécieux;
    private boolean modeSportif;

    // Constructeur par défaut
    public UpdateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prenom;
    }

    public void setPrénom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getRégimeSpécieux() {
        return régimeSpécieux;
    }

    public void setRégimeSpécieux(String régimeSpécieux) {
        this.régimeSpécieux = régimeSpécieux;
    }

    public boolean isModeSportif() {
        return modeSportif;
    }

    public void setModeSportif(boolean modeSportif) {
        this.modeSportif = modeSportif;
    }

    // Ajoutez d'autres getters et setters si nécessaire

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sexe='" + sexe + '\'' +
                ", taille='" + taille + '\'' +
                ", poids='" + poids + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", régimeSpécieux='" + régimeSpécieux + '\'' +
                ", modeSportif=" + modeSportif +
                '}';
    }
}
