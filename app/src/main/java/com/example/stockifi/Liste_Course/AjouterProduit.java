package com.example.stockifi.Liste_Course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.stockifi.R;

public class AjouterProduit extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);



        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ajout);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        Spinner spinnerPoidAjout = findViewById(R.id.spinner_poid_ajout);

        // Créer un ArrayAdapter en utilisant les éléments du tableau défini dans les ressources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        // Spécifier le layout à utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer l'adaptateur au Spinner
        spinnerPoidAjout.setAdapter(adapter);
    }
}