package com.example.stockifi;

import java.io.Serializable;
import java.util.Date;

public class RegisterRequest implements Serializable {

    private String prénom;
    private String nom;
    private String email;
    private String password;
    private String sexe;
    private String taille;
    private String poids;
    private Date dateDeNaissance;
    private String régimeSpécieux;
    private boolean modeSportif;

    // Constructors
    public RegisterRequest() {
    }

    public RegisterRequest(String prénom, String nom, String email, String password, String régimeSpécieux, boolean modeSportif) {
        this.prénom = prénom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.sexe =  null;
        this.taille =  null;
        this.poids =  null;
        this.dateDeNaissance = null;
        this.régimeSpécieux = régimeSpécieux;
        this.modeSportif = modeSportif;
    }

    // Getter and Setter methods
    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    // You can also override toString() for debugging or logging purposes
    @Override
    public String toString() {
        return "RegisterRequest{" +
                "prénom='" + prénom + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", régimeSpécieux='" + régimeSpécieux + '\'' +
                ", modeSportif=" + modeSportif +
                // Add other fields for debugging or logging
                '}';
    }
}
