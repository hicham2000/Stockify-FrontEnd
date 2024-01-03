package com.example.stockifi.recettes;

import java.util.List;

public class FilterValues {
    private List<String> selectedRegimes;
    private int tempsDePreparation;
    private List<String> selectedProduits;

    public List<String> getSelectedRegimes() {
        return selectedRegimes;
    }

    public void setSelectedRegimes(List<String> selectedRegimes) {
        this.selectedRegimes = selectedRegimes;
    }

    public int getTempsDePreparation() {
        return tempsDePreparation;
    }

    public void setTempsDePreparation(int tempsDePreparation) {
        this.tempsDePreparation = tempsDePreparation;
    }

    public List<String> getSelectedProduits() {
        return selectedProduits;
    }

    public void setSelectedProduits(List<String> selectedProduits) {
        this.selectedProduits = selectedProduits;
    }
}
