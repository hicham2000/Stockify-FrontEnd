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
import com.example.stockifi.R;

import java.util.ArrayList;

public class ListeproduitAdapter extends ArrayAdapter<ProduitALaListe> {

    private ArrayList<ProduitALaListe> objets;

    public ListeproduitAdapter(Context context, int resource, ArrayList<ProduitALaListe>objets){
        super(context, resource,objets);
        this.objets=objets;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.liste_produit,parent,false);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.image_prod);
        imageView.setBackgroundResource(objets.get(position).getImage());
        TextView viewTitle1=(TextView) convertView.findViewById(R.id.nom_produit);
        viewTitle1.setText(objets.get(position).getIntitule());

        ImageView imageView1=(ImageView) convertView.findViewById(R.id.ajouterprodu1);
        //  imageView1.setBackgroundResource(objets.get(position).getImageAjout());
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AjouterProduit_ListeProduit.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
