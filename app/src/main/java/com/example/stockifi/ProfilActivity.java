package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    private LinearLayout pickDateButton;
    private TextView date_naissace;

    private int year, month, day;
    private boolean titreSelectionne = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        final EditText editText = findViewById(R.id.editTexte_taille);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
            }
        });





        Switch yourSwitch = findViewById(R.id.switch1);
        Switch yourSwitch2 = findViewById(R.id.switch2);
        Switch yourSwitch3 = findViewById(R.id.switch3);
        Switch yourSwitch4 = findViewById(R.id.switch4);


        yourSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch.getThumbDrawable().setTint(thumbColor);
            }
        });

        yourSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch2.getThumbDrawable().setTint(thumbColor);
            }
        });

        yourSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch3.getThumbDrawable().setTint(thumbColor);
            }
        });

        yourSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch4.getThumbDrawable().setTint(thumbColor);
            }
        });


        TextView changePass=findViewById(R.id.change_pass);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Définissez l'Intent pour passer à l'écran de destination
                Intent intent = new Intent(ProfilActivity.this, ChangerPassword.class);
                startActivity(intent);
            }
        });
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Récupérer le RadioButton sélectionné
            RadioButton selectedRadioButton = findViewById(checkedId);

            // Faire quelque chose en fonction de la sélection
            if (selectedRadioButton != null) {
                String selectedText = selectedRadioButton.getText().toString();
                showToast("Unité sélectionnée : " + selectedText);
            }
        });







        Spinner spinnerGender = findViewById(R.id.spinner_gender);
        Spinner spinnerTaille= findViewById(R.id.spinner_taille);
        Spinner spinnerPoids= findViewById(R.id.spinner_poids);
        Spinner spinnerRegime= findViewById(R.id.spinner_regime);
        Spinner spinnerDevise= findViewById(R.id.devise);
        Spinner spinnerDate= findViewById(R.id.spinner_date);
        Spinner spinnerQuantite= findViewById(R.id.spinner_mesure);
        Spinner spinnerPerem= findViewById(R.id.spinner_date_per);

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

        ArrayAdapter<CharSequence> adapter_regime = ArrayAdapter.createFromResource(
                this,
                R.array.regime_speciaux,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_devise = ArrayAdapter.createFromResource(
                this,
                R.array.devise,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_date = ArrayAdapter.createFromResource(
                this,
                R.array.date,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_mesure = ArrayAdapter.createFromResource(
                this,
                R.array.mesure,
                android.R.layout.simple_spinner_item
        );

        // Spécifier la disposition de la liste déroulante
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_taille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_poids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_regime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_devise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_mesure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Appliquer l'adaptateur au Spinner
        spinnerGender.setAdapter(adapter);
        spinnerTaille.setAdapter(adapter_taille);
        spinnerPoids.setAdapter(adapter_poids);
        spinnerRegime.setAdapter(adapter_regime);
        spinnerDevise.setAdapter(adapter_devise);
        spinnerDate.setAdapter(adapter_date);
        spinnerQuantite.setAdapter(adapter_mesure);
        spinnerPerem.setAdapter(adapter_date);

        pickDateButton = findViewById(R.id.date_naissance);
        date_naissace=findViewById(R.id.dateNaissance);
     

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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                date_naissace.setText("       "  +String.valueOf(year)+"."+String.valueOf(month+1)+"."+String.valueOf(day));

            }
        },year, month, day);

        dialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }






}