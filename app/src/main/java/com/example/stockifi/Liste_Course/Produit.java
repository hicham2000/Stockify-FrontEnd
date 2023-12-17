package com.example.stockifi.Liste_Course;

public class Produit {

    private int id;
    private String intitule;

    private String quantite;
    private String uniteMesure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public Produit(int id, String intitule, String quantite, String uniteMesure) {
        this.id = id;
        this.intitule = intitule;
        this.quantite = quantite;
        this.uniteMesure = uniteMesure;
    }
}
