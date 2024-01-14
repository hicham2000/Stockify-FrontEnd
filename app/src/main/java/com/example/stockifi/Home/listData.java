package com.example.stockifi.Home;

public class listData {
    private int id;
    private String intitule;
    private String dateExpiration;
    private String imageUrl;

    public listData(int id, String intitule, String dateExpiration, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.intitule = intitule;
        this.dateExpiration = dateExpiration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
