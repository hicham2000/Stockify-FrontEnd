package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.stockifi.R;

public class UpdateRepas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_repas);
        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
    }
}