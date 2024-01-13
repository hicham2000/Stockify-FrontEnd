package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.stockifi.R;

public class UpdateRepas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_repas);
        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_updaterepas);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });
    }
}