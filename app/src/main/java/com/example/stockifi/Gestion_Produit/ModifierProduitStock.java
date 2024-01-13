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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ModifierProduitStock extends AppCompatActivity {

    LinearLayout alert ;
    LinearLayout peremtion ;
    TextView alerttext;
    TextView peremtiontext;

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
        setContentView(R.layout.activity_modifier_produit_stock);



        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button_ajout_modif);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        peremtion=findViewById(R.id.peremtionDateProduit_modif);
        peremtiontext = findViewById(R.id.peremtiontextproduit_modif);

        peremtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogPeremstion();
            }
        });

        alert=findViewById(R.id.AlerteProd_modif);
        alerttext = findViewById(R.id.alertetextproduit_modif);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogalert();
            }
        });

        Button buttonannuler = findViewById(R.id.button_annulerprod_modif);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifierProduitStock.this, ListeProduit.class);
                startActivity(intent);
            }
        });

        Button buttonajouter = findViewById(R.id.button_validerProd_modif);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifierProduitStock.this, ListeProduit.class);
                startActivity(intent);
            }
        });


        EditText nom = findViewById(R.id.editTexte_t_modif);
        //Spinner Cat = findViewById(R.id.spinner_categorie_modif);
        Spinner Mesure = findViewById(R.id.spinner_poid_ajoutModif1_modif);

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

        TextView Alerte = findViewById(R.id.alertetextproduit_modif);
        TextView peremption = findViewById(R.id.peremtiontextproduit_modif);
        EditText Prix = findViewById(R.id.Prix_produit_modif);
        EditText QteC = findViewById(R.id.quantiteCritique_modif);
        EditText Qte = findViewById(R.id.quant_ajoutModif_modif);
        int produitId = 3;

        String url = "http://10.0.2.2:1111/stocks/productid/" + produitId;
        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String name = jsonResponse.getString("intitule");
                            String qte = jsonResponse.getString("quantite");
                            String mesure = jsonResponse.getString("uniteDeMesure");
                            String prix = jsonResponse.getString("prix");
                            String per = jsonResponse.getString("dateExpiration");
                            String alerte = jsonResponse.getString("dateAlerte");
                            String qteC = jsonResponse.getString("quantiteCritique");
                            nom.setText(name);
                            Alerte.setText(alerte);
                            peremption.setText(per);
                            Prix.setText(prix);
                            QteC.setText(qteC);
                            Qte.setText(qte);
                            Mesure.setSelection(getIndex(Mesure, mesure));
                            System.out.println(getIndex(Mesure, mesure));


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hhhhhhhhhcxvxcvxcvx");

            }
        });

        queue1.add(stringRequest);

        Button buttonValiderProd = findViewById(R.id.button_validerProd_modif);
        Button buttonModifierProd = findViewById(R.id.button_validerProd_modif);

        MyApp myApp = (MyApp) getApplication();
        int stockId = myApp.getUser_stock_id();


        buttonModifierProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to send the POST request
                RequestQueue queue = Volley.newRequestQueue(ModifierProduitStock.this);
                String url = "http://10.0.2.2:1111/stocks/" + stockId + "/products/" + produitId;

                // Example data to send in the request body
                JSONObject request = new JSONObject();
                try {
                    // Set the properties based on your Produit entity
                    request.put("intitule", nom.getText().toString());
                    request.put("quantite", Qte.getText().toString());
                    request.put("uniteDeMesure", Mesure.getSelectedItem().toString());
                    request.put("dateExpiration", peremption.getText().toString());
                    request.put("prix", Prix.getText().toString());
                    request.put("dateAlerte", Alerte.getText().toString());
                    request.put("quantiteCritique", QteC.getText().toString());
                    System.out.println(request);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Make sure to replace this with your actual parameters
                JsonObjectRequest modifierProduit = new JsonObjectRequest(Request.Method.PUT,
                        url,
                        request,
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
                queue.add(modifierProduit);

            }
        });


    }
    private void openDialogPeremstion () {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                peremtiontext.setText(selectedDate);


            }
        }, year, month, day);

        dialog.show();
    }

    private void openDialogalert () {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                alerttext.setText(selectedDate);


            }
        }, year, month, day);

        dialog.show();
    }
}



