package com.example.stockifi.Liste_Course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stockifi.R;

public class information_produit_listeCourse extends AppCompatActivity {

    TextView nom;
    TextView quantite;

    TextView spinnerPoidAjoutmodif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_produit_liste_course);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_inform);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });



        spinnerPoidAjoutmodif = findViewById(R.id.spinner_poid_ajoutModif1Inf);
        nom=findViewById(R.id.editTexte_modifProduitInf);
        quantite=findViewById(R.id.quant_ajoutModifInf);


        Bundle extras=getIntent().getExtras();
        int id= new Integer(extras.getInt("id"));
        String titre=new String(extras.getString("intitule"));
        double quantitee = extras.getDouble("quantite");
        String mesurePoid=new String(extras.getString("mesure"));

        nom.setText(titre);
        String quantiteString = String.valueOf(quantitee);
        quantite.setText(quantiteString);
        spinnerPoidAjoutmodif.setText(mesurePoid);


    }
}