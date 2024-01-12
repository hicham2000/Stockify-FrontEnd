package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

        int produitId = 4;

        String url1 = "http://10.0.2.2:1111/stocks/productid/" + produitId;
        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
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

        queue1.add(stringRequest1);

    }




}