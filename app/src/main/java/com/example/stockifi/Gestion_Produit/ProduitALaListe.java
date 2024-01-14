package com.example.stockifi.Gestion_Produit;

public class ProduitALaListe {

    Long id;
    String mesure;
    String imageUrl;
    String intitule;
    //   Integer imageUrlAjout;


    public ProduitALaListe(Long id, String imageUrl, String intitule, String mesure) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.intitule = intitule;
        this.mesure= mesure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmesure() {
        return mesure;
    }



    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    //  public Integer getimageUrlAjout() {
    //    return imageUrlAjout;
    //  }

    //  public void setimageUrlAjout(Integer imageUrlAjout) {
    //    this.imageUrlAjout = imageUrlAjout;
    //  }
}
