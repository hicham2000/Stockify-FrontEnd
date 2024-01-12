package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.stockifi.R;

public class ViewRepas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_repas);
        EditText editText = findViewById(R.id.editTexte_t);
        EditText editTextt = findViewById(R.id.cat);
        editText.setEnabled(false);
        editTextt.setEnabled(false);
        Button buttonAnnuler = findViewById(R.id.button_supprimer);
        buttonAnnuler.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));


    }
}