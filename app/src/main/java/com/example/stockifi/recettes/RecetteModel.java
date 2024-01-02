package com.example.stockifi.recettes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecetteModel implements Serializable, Cloneable{
    private String imageUrl;
    private String recetteName;
    private int duration;
    private int ingredientsMissing;

    private boolean isFavoris;

    private List<String> ingredientList;

    public RecetteModel(String imageUrl, String recetteName, int duration, int ingredientsMissing, boolean isFavoris) {
        this.imageUrl = imageUrl;
        this.recetteName = recetteName;
        this.duration = duration;
        this.ingredientsMissing = ingredientsMissing;
        this.isFavoris = isFavoris;
    }

    public RecetteModel(RecetteModel recette) {
        this.imageUrl = recette.getImageUrl();
        this.recetteName = recette.getRecetteName();
        this.duration = recette.getDuration();
        this.ingredientsMissing = recette.getIngredientsMissing();
        this.isFavoris = recette.isFavoris();
    }
    @Override
    public RecetteModel clone() {
        // Perform a shallow copy (copy primitive types and references)
        RecetteModel cloned = new RecetteModel(this);

        // Deep copy the ingredientList using a loop
        if (this.ingredientList != null) {
            cloned.ingredientList = new ArrayList<>(this.ingredientList.size());
            for (String ingredient : this.ingredientList) {
                cloned.ingredientList.add(ingredient);
            }
        }

        return cloned;
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

    @Override
    public String toString() {
        return "RecetteModel{" +
                //"imageUrl='" + imageUrl + '\'' +
                ", recetteName='" + recetteName + '\'' +
                //", duration=" + duration +
                //", ingredientsMissing=" + ingredientsMissing +
                ", favoris=" + isFavoris +
                '}';
    }
}
