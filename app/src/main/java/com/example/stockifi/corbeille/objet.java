package com.example.stockifi.corbeille;
import android.os.Parcel;
import android.os.Parcelable;

public class objet implements Parcelable {
    private int id;
    private String intitule;

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

    public objet(int id, String intitule, boolean check) {
        this.id = id;
        this.intitule = intitule;
        this.check=check;
    }
    public objet(int id){
        this.id = id;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String toString() {
        return "Item{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                '}';
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(intitule);
        dest.writeInt(id);
    }
    public static final Parcelable.Creator<objet> CREATOR = new Parcelable.Creator<objet>() {
        public objet createFromParcel(Parcel in) {
            return new objet(in);
        }

        public objet[] newArray(int size) {
            return new objet[size];
        }
    };
    private objet(Parcel in) {
        intitule = in.readString();
        id = in.readInt();
    }
}