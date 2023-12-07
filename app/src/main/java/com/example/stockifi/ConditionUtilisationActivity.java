package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConditionUtilisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_utilisation);

        ImageView toolbarBackButton_condi = findViewById(R.id.toolbar_back_button_condi);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_condi.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });
    }



}