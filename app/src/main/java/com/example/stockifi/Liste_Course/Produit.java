package com.example.stockifi.Liste_Course;

public class Produit {

    private int id;
    private String intitule;

    private double quantite;
    private String uniteMesure;

    private Boolean check;

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


    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public Produit(int id, String intitule, double quantite, String uniteMesure,boolean check) {
        this.id = id;
        this.intitule = intitule;
        this.quantite = quantite;
        this.uniteMesure = uniteMesure;
        this.check=check;
    }

    public Produit(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", quantite=" + quantite +
                ", uniteMesure='" + uniteMesure + '\'' +
                '}';
    }
}
