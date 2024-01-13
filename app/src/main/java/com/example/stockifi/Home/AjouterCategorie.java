package com.example.stockifi.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AjouterCategorie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);
    }
}
/*Button ajouterCategorie = findViewById(R.id.button_ajouter);

        EditText categorieNameEdit = findViewById(R.id.catégorie);
        String categorieName = categorieNameEdit.getText().toString();
        ajouterCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(AjouterCategorie.this);
                String url = "http://10.0.2.2:1111/categorieDeProduits/categorie";
                requestCategorie = new JSONObject();
                try {
                    // Set the properties based on your Produit entity
                    requestCategorie.put("intitule", categorieName);

                    System.out.println(requestCategorie);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest ajouterCatégorie = new JsonObjectRequest(Request.Method.POST,
                        url,
                        requestCategorie,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(ajouterCatégorie);
            }
        });*/