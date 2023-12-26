package com.example.stockifi.Gestion_Produit;

public class ProduitALaListe {
    Integer image;
    String intitule;
    //   Integer imageAjout;

    public ProduitALaListe(Integer image, String intitule) {
        this.image = image;
        this.intitule = intitule;
        // this.imageAjout = imageAjout;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    //  public Integer getImageAjout() {
    //    return imageAjout;
    //  }

    //  public void setImageAjout(Integer imageAjout) {
    //    this.imageAjout = imageAjout;
    //  }
}
