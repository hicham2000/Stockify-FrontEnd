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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

    }




}