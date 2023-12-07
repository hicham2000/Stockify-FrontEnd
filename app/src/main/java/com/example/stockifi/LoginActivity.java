package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button boutonLogin = findViewById(R.id.button_login);
        TextView to_inscription = findViewById(R.id.textViewCreateCompte) ;


        boutonLogin.setOnClickListener(new View.OnClickListener() {



            // Créer un Intent pour passer à une autre activité
            @Override
            public void onClick(View view) {
                // Lorsque le bouton est cliqué, ouvrir l'écran profil
                Intent intent = new Intent(LoginActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
        });
        TextView textViewForgottenPwd = findViewById(R.id.textViewForgottenPwd);
        textViewForgottenPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for the "Forgotten Password" TextView
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });


        to_inscription.setOnClickListener(new View.OnClickListener() {



            // Créer un Intent pour passer à une autre activité
            @Override
            public void onClick(View view) {
                // Lorsque le bouton est cliqué, ouvrir l'écran profil
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}



