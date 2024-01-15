package com.example.stockifi.Home;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AjouterCategorie extends AppCompatActivity {
    String apiUrl = "";
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Parse the JSON response if it's in JSON format
                    JSONObject jsonResponse = new JSONObject(response);

                    // Example: If the server returns a status field in the response
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        // Category added successfully
                        // You can put additional code here if needed
                    } else {
                        // Category addition failed
                        // You can put additional code here if needed
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                // Handle error response from the server
            }
        });

        requestQueue.add(stringRequest);
    }
}
