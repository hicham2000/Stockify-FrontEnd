package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.R;
import com.example.stockifi.Repas.ViewRepas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationsProduitStock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations_produit_stock);


        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button_ajout_info);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ajout_info);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });




        TextView nom = findViewById(R.id.nom_produit);
        TextView Cat = findViewById(R.id.cat_produit);
        TextView Mesure = findViewById(R.id.mesure_produit);
        TextView Alerte = findViewById(R.id.alerte_produit);
        TextView peremption = findViewById(R.id.per_produit);
        TextView Prix = findViewById(R.id.prix_produit);
        TextView QteC = findViewById(R.id.qteC_produit);
        TextView Qte = findViewById(R.id.qte_produit);

        Intent intent = getIntent();
        int produitId = Integer.parseInt(intent.getStringExtra("produitid"));

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
                                Mesure.setText(mesure);



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

        Button editerProduit = findViewById(R.id.button_modifier_info);

        editerProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(InformationsProduitStock.this, ModifierProduitStock.class);
                intent.putExtra("produitid", Integer.toString(produitId));
                startActivity(intent);



            }
        });

        MyApp myApp = (MyApp) getApplication();
        int stockId = myApp.getUser_stock_id();

        Button supprimerProduit = findViewById(R.id.button_supprimer_info);

        supprimerProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to send the POST request
                RequestQueue queue = Volley.newRequestQueue(InformationsProduitStock.this);
                String url = "http://10.0.2.2:1111/stocks/" + stockId + "/products/" + produitId;

                // Example data to send in the request body
                JSONObject request = new JSONObject();
                try {
                    // Set the properties based on your Produit entity
                    request.put("intitule", nom.getText().toString());
                    request.put("quantite", Qte.getText().toString());
                    //request.put("uniteDeMesure", Mesure.toString());
                    request.put("dateExpiration", "2024.01.27");
                    request.put("prix", Prix.getText().toString());
                    request.put("dateAlerte", "2024.01.29");
                    request.put("quantiteCritique", QteC.getText().toString());
                    request.put("is_deleted",1);
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

                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(InformationsProduitStock.this, HomeActivity.class);
                startActivity(intent);

            }
        });


    }




}