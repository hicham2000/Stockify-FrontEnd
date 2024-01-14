package com.example.stockifi.Gestion_Produit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import java.util.ArrayList;

public class ListeproduitAdapter extends ArrayAdapter<ProduitALaListe> {

    private ArrayList<ProduitALaListe> objets;

    public ListeproduitAdapter(Context context,  ArrayList<ProduitALaListe>objets){
        super(context, 0,objets);
        this.objets=objets;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.liste_produit,parent,false);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.image_prod);
      //  imageView.setBackgroundResource(objets.get(position).getImage());
        TextView viewTitle1=(TextView) convertView.findViewById(R.id.nom_produit_listeglobal);
        viewTitle1.setText(objets.get(position).getIntitule());

        ImageView imageView1=(ImageView) convertView.findViewById(R.id.ajouterprodu1);
        //  imageView1.setBackgroundResource(objets.get(position).getImageAjout());
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProduitALaListe produit = getItem(position);
                Intent intent = new Intent(getContext(), AjouterProduit_ListeProduit.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sender","Global");
                intent.putExtra("id", produit.getId());
                intent.putExtra("intitule", produit.getIntitule());
                intent.putExtra("imageUrl", produit.getimageUrl());
                intent.putExtra("mesure", produit.getmesure());
                getContext().startActivity(intent);
                System.out.println(produit.getmesure());

            }
        });

        return convertView;
    }
}
