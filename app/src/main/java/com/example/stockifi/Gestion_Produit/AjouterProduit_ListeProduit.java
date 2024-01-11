package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stockifi.R;
import com.example.stockifi.Repas.ajouter_repas;
import com.example.stockifi.Repas.ingredients;

import java.util.Calendar;

public class AjouterProduit_ListeProduit extends AppCompatActivity {

    LinearLayout peremtion;
    LinearLayout alert;
    TextView peremtiontext;

    TextView alerttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit_liste_produit);

        peremtion=findViewById(R.id.peremtionDateProduit);
        peremtiontext = findViewById(R.id.peremtiontextproduit);

        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button_ajout);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        peremtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogPeremstion();
            }
        });

        alert=findViewById(R.id.AlerteProd);
        alerttext = findViewById(R.id.alertetextproduit);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogalert();
            }
        });

        Button buttonannuler = findViewById(R.id.button_annulerprod);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjouterProduit_ListeProduit.this, ListeProduit.class);
                startActivity(intent);
            }
        });

        Button buttonajouter = findViewById(R.id.button_validerProd);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjouterProduit_ListeProduit.this, ListeProduit.class);
                startActivity(intent);
            }
        });
    }

    private void openDialogPeremstion(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                peremtiontext.setText(selectedDate);


            }
        },year, month, day);

        dialog.show();
    }

    private void openDialogalert(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                alerttext.setText(selectedDate);


            }
        },year, month, day);

        dialog.show();
    }
}