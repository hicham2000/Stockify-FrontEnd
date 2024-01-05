package com.example.stockifi.recettes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecetteModel implements Serializable, Cloneable{
    private Long id;
    private String imageUrl;
    private String recetteName;
    private String description;
    private int duration;
    private int ingredientsMissing;
    private int quantiteEnStock;
    private List<IngredientInfo> ingredientsList = new ArrayList<>();
    private ValeurNutritionnel valeurNutritionnel;
    private boolean isFavoris;

    public static class ValeurNutritionnel implements Serializable {
        private Long id;
        private double proteine;
        private double carbohydrate;
        private double lipide;
        private double enegie;
        private double sucre;
        private double fibre;
    }

    public static class IngredientInfo implements Serializable {
        private Long id;
        private String intitule;
        private Double quantity;
        private boolean isEnough;
    }

    public RecetteModel(JSONObject recetteObject) {
        try {
            this.id = recetteObject.getLong("id");
            this.recetteName = recetteObject.getString("intitule");
            this.description = recetteObject.getString("description");
            this.duration = recetteObject.getInt("dureeTotal");
            this.quantiteEnStock = recetteObject.getInt("quantiteEnStock");
            this.imageUrl = recetteObject.getString("imageUrl");
            this.ingredientsMissing = recetteObject.getInt("nombreIngredientsManquantes");
            this.isFavoris = recetteObject.getBoolean("favoris");

            // Extract ingredients
            JSONArray ingredientsArray = recetteObject.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                IngredientInfo ingredientInfo = new IngredientInfo();
                ingredientInfo.id = ingredientObject.getLong("id");
                ingredientInfo.intitule = ingredientObject.getString("intitule");
                ingredientInfo.quantity = ingredientObject.getDouble("quantity");
                ingredientInfo.isEnough = ingredientObject.getBoolean("enough");
                this.ingredientsList.add(ingredientInfo);
            }

            // Extract nutritional values
            JSONObject valeurNutritionnelObject = recetteObject.getJSONObject("valeurNutritionnel");
            this.valeurNutritionnel = new ValeurNutritionnel();
            this.valeurNutritionnel.id = valeurNutritionnelObject.getLong("id");
            this.valeurNutritionnel.proteine = valeurNutritionnelObject.getDouble("proteine");
            this.valeurNutritionnel.carbohydrate = valeurNutritionnelObject.getDouble("carbohydrate");
            this.valeurNutritionnel.lipide = valeurNutritionnelObject.getDouble("lipide");
            this.valeurNutritionnel.enegie = valeurNutritionnelObject.getDouble("enegie");
            this.valeurNutritionnel.sucre = valeurNutritionnelObject.getDouble("sucre");
            this.valeurNutritionnel.fibre = valeurNutritionnelObject.getDouble("fibre");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRecetteName() {
        return recetteName;
    }

    public String getDescription() {
        return description;
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
