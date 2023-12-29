package com.example.stockifi.recettes;

public class RecetteModel {
    private String imageUrl;
    private String recetteName;
    private int duration;
    private int ingredientsMissing;

    private boolean isFavoris;

    public RecetteModel(String imageUrl, String recetteName, int duration, int ingredientsMissing, boolean isFavoris) {
        this.imageUrl = imageUrl;
        this.recetteName = recetteName;
        this.duration = duration;
        this.ingredientsMissing = ingredientsMissing;
        this.isFavoris = isFavoris;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecetteName() {
        return recetteName;
    }

    public int getDuration() {
        return duration;
    }

    public int getIngredientsMissing() {
        return ingredientsMissing;
    }

    public boolean isFavoris() {
        return this.isFavoris;
    }

    public void setFavoris(boolean isFavoris){
        this.isFavoris = isFavoris;
    }
}
