package com.example.stockifi.recettes;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecetteModel implements Serializable, Cloneable{
    private String imageUrl;
    private String recetteName;
    private String description;
    private int duration;
    private int ingredientsMissing;
    private List<IngredientInfo> ingredientsList = new ArrayList<>();
    private ValeurNutritionnel valeurNutritionnel;
    private boolean isFavoris;

    public static class ValeurNutritionnel {
        private Long id;
        private double proteine;
        private double carbohydrate;
        private double lipide;
        private double enegie;
        private double sucre;
        private double fibre;
    }

    public static class IngredientInfo {
        private Long id;
        private String intitule;
        private Double quantity;
        private boolean isEnough;
    }

    public RecetteModel(String imageUrl, String recetteName, String description, int duration, int ingredientsMissing, List<IngredientInfo> ingredientsList, boolean isFavoris) {
        this.imageUrl = imageUrl;
        this.recetteName = recetteName;
        this.description = description;
        this.duration = duration;
        this.ingredientsMissing = ingredientsMissing;
        this.ingredientsList = ingredientsList;
        this.isFavoris = isFavoris;
    }

    public RecetteModel(RecetteModel recette) {
        this.imageUrl = recette.getImageUrl();
        this.recetteName = recette.getRecetteName();
        this.duration = recette.getDuration();
        this.ingredientsMissing = recette.getIngredientsMissing();
        this.isFavoris = recette.isFavoris();
    }

    public RecetteModel(JSONObject response) {
        try {
            JSONArray recettesArray = response.getJSONArray("recettes");

            if (recettesArray.length() > 0) {
                JSONObject recetteObject = recettesArray.getJSONObject(0); // Assuming you want the first recipe
                this.recetteName = recetteObject.getString("intitule");
                this.description = recetteObject.getString("description");
                this.duration = recetteObject.getInt("dureeTotal");
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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
//    @Override
//    public RecetteModel clone() {
//        // Perform a shallow copy (copy primitive types and references)
//        RecetteModel cloned = new RecetteModel(this);
//
//        // Deep copy the ingredientList using a loop
//        if (this.ingredientList != null) {
//            cloned.ingredientList = new ArrayList<>(this.ingredientList.size());
//            for (IngredientInfo ingredient : this.ingredientList) {
//                cloned.ingredientList.add(ingredient);
//            }
//        }
//
//        return cloned;
//    }
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
