package com.example.stockifi.recettes;

import java.util.List;

public class FilterValues {
    private List<String> regimeSpeciaux;
    private String tempsDePreparation;
    private List<String> nomsDesIngredientPreferes;

    public List<String> getSelectedRegimes() {
        return regimeSpeciaux;
    }

    public void setSelectedRegimes(List<String> selectedRegimes) {
        this.regimeSpeciaux = selectedRegimes;
    }

    public String getTempsDePreparation() {
        return tempsDePreparation;
    }

    public void setTempsDePreparation(int tempsDePreparation) {
        this.tempsDePreparation = String.valueOf(tempsDePreparation);
    }

    public List<String> getSelectedProduits() {
        return nomsDesIngredientPreferes;
    }

    public void setSelectedProduits(List<String> selectedProduits) {
        this.nomsDesIngredientPreferes = selectedProduits;
    }
}
