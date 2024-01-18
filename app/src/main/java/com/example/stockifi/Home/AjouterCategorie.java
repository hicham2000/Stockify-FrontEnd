package com.example.stockifi.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AjouterCategorie extends AppCompatActivity {
    String apiUrl = "http://10.0.2.2:1111/categorieDeProduits/categorie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);
        EditText editCatégorieText = findViewById(R.id.catégorie) ;
        Button ajouterCat = findViewById(R.id.button_ajouter);
        ajouterCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String catégorieName = editCatégorieText.getText().toString();
                try {
                    ajouterCatégorie(catégorieName);
                    Intent intent = new Intent(AjouterCategorie.this, HomeActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    public void ajouterCatégorie(String categoryName) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject jsonParams = new JSONObject();
        jsonParams.put("intitule", categoryName);
        JsonObjectRequest ajouterCat = new JsonObjectRequest(Request.Method.POST,
                apiUrl,
                jsonParams,
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
        requestQueue.add(ajouterCat);
    }
}