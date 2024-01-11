package com.example.stockifi.Liste_Course;

import android.os.Parcel;
import android.os.Parcelable;

public class Produit implements Parcelable {

    private int id;
    private String intitule;

    private double quantite;
    private String uniteMesure;

    private Boolean check;

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


    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public Produit(int id, String intitule, double quantite, String uniteMesure,boolean check) {
        this.id = id;
        this.intitule = intitule;
        this.quantite = quantite;
        this.uniteMesure = uniteMesure;
        this.check=check;
    }

    public Produit(int id){
        this.id = id;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", quantite=" + quantite +
                ", uniteMesure='" + uniteMesure + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(intitule);
        dest.writeInt(id);
        dest.writeString(uniteMesure);
        dest.writeDouble(quantite);
    }

    public static final Parcelable.Creator<Produit> CREATOR = new Parcelable.Creator<Produit>() {
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    private Produit(Parcel in) {
        intitule = in.readString();
        id = in.readInt();
        uniteMesure = in.readString();
        quantite = in.readDouble();
    }
}
