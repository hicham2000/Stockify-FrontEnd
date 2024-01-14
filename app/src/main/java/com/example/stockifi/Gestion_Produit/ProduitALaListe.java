package com.example.stockifi.Gestion_Produit;

public class ProduitALaListe {

    Long id;
    String image;
    String intitule;
    //   Integer imageAjout;


    public ProduitALaListe(Long id, String image, String intitule) {
        this.id = id;
        this.image = image;
        this.intitule = intitule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
