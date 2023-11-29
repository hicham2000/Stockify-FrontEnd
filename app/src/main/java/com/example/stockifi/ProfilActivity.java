package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    private LinearLayout pickDateButton;
    private TextView date_naissace;

    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Spinner spinnerGender = findViewById(R.id.spinner_gender);
        Spinner spinnerTaille= findViewById(R.id.spinner_taille);
        Spinner spinnerPoids= findViewById(R.id.spinner_poids);

        // Définir les options pour le Spinner (ajoutez "Genre" en tant que première entrée)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.genre,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_taille = ArrayAdapter.createFromResource(
                this,
                R.array.taille,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_poids = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        // Spécifier la disposition de la liste déroulante
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_taille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_poids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Appliquer l'adaptateur au Spinner
        spinnerGender.setAdapter(adapter);
        spinnerTaille.setAdapter(adapter_taille);
        spinnerPoids.setAdapter(adapter_poids);

        pickDateButton = findViewById(R.id.date_naissance);
        date_naissace=findViewById(R.id.dateNaissance);
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        // Mettez à jour le TextView avec la date actuelle



        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialog();
            }
        });


    }

    private void openDialog(){
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                date_naissace.setText("       "  +String.valueOf(year)+"."+String.valueOf(month+1)+"."+String.valueOf(day));

            }
        },2023,0,15 );

        dialog.show();
    }






}