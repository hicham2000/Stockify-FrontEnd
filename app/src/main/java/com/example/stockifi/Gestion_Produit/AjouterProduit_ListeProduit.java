package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AjouterProduit_ListeProduit extends AppCompatActivity {

    LinearLayout peremtion;
    LinearLayout alert;
    TextView peremtiontext;

    TextView alerttext;
    String intituleValue="";
    String mesureValue="tonne";
    int mesureInt=0;

    private int getIndex(Spinner spinner, String string) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();

        int s = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().equalsIgnoreCase(string)) {
                s = i;
            }

        }
        return s;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit_liste_produit);



        Button buttonValiderProd = findViewById(R.id.button_validerProd);
        EditText intitule = findViewById(R.id.editTexte_t);
        EditText quantite = findViewById(R.id.quant_ajoutModif);
        EditText prix = findViewById(R.id.Prix_produit);
        TextView peremption = findViewById(R.id.peremtiontextproduit);
        TextView alerte = findViewById(R.id.alertetextproduit);
        EditText critique = findViewById(R.id.quantiteCritique);



        Spinner Mesure = findViewById(R.id.spinner_poid_ajoutModif1);

        // Créer un ArrayAdapter en utilisant les éléments du tableau défini dans les ressources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        // Spécifier le layout à utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer l'adaptateur au Spinner
        Mesure.setAdapter(adapter);

        Intent intent = getIntent();
        String sender = intent.getStringExtra("sender");
        if (sender.equals("Global")){
            intituleValue = intent.getStringExtra("intitule");
            mesureValue = intent.getStringExtra("mesure");

        } else{
            intituleValue = "";
            mesureValue = "tonne";
        }

        intitule.setText(intituleValue);
        mesureInt = getIndex(Mesure,mesureValue);
        Mesure.setSelection(mesureInt);

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




        MyApp myApp = (MyApp) getApplication();
        int stockId = myApp.getUser_stock_id();
        buttonValiderProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to send the POST request
                RequestQueue queue = Volley.newRequestQueue(AjouterProduit_ListeProduit.this);
                String url = "http://10.0.2.2:1111/stocks/"+stockId+"/products";

                // Example data to send in the request body
                JSONObject requestProduits = new JSONObject();
                try {
                    // Set the properties based on your Produit entity
                    requestProduits.put("intitule", intitule.getText().toString());
                    requestProduits.put("quantite", quantite.getText().toString());
                    requestProduits.put("uniteDeMesure", Mesure.getSelectedItem().toString());
                    requestProduits.put("dateExpiration", peremption.getText().toString());
                    requestProduits.put("prix", prix.getText().toString());
                    requestProduits.put("dateAlerte", alerte.getText().toString());
                    requestProduits.put("quantiteCritique", critique.getText().toString());
                    System.out.println(requestProduits);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make sure to replace this with your actual parameters
                JsonObjectRequest ajouterProduit = new JsonObjectRequest(Request.Method.POST,
                        url,
                        requestProduits,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle the response from the server
                                // You might want to parse and process the response JSON here
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        // You might want to display an error message to the user
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(ajouterProduit);

                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(AjouterProduit_ListeProduit.this, HomeActivity.class);
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