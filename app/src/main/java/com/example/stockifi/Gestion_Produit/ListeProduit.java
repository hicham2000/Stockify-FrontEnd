package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.stockifi.R;

import java.util.ArrayList;


public class ListeProduit extends AppCompatActivity {

    ListView list_produit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);

        list_produit=findViewById(R.id.myListViewProduit);

        ArrayList<ProduitALaListe> objets=new ArrayList<>();
        objets.add(new ProduitALaListe(R.drawable.profil,"lait"));
        ListeproduitAdapter adapter=new ListeproduitAdapter(getApplicationContext(),R.layout.liste_produit,objets);
        list_produit.setAdapter(adapter);
    }
}