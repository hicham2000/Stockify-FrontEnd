package com.example.stockifi.Home;

public class listData2 {
    private int id;
    private String intitule;
    private String datePeremtion;
    private String imageUrl;

    public listData2(int id, String intitule, String datePeremtion, String imageUrl) {
        this.id = id;
        this.intitule = intitule;
        this.datePeremtion = datePeremtion;
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

    public String getDatePeremtion() {
        return datePeremtion;
    }

    public void setDatePeremtion(String datePeremtion) {
        this.datePeremtion = datePeremtion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
