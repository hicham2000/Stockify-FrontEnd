package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.stockifi.R;

import java.util.ArrayList;


public class ListeProduit extends AppCompatActivity {

    ListView list_produit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_listeproduit);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        list_produit=findViewById(R.id.myListViewProduit);

        ArrayList<ProduitALaListe> objets=new ArrayList<>();
        objets.add(new ProduitALaListe(R.drawable.profil,"lait"));
        ListeproduitAdapter adapter=new ListeproduitAdapter(getApplicationContext(),R.layout.liste_produit,objets);
        list_produit.setAdapter(adapter);
    }
}