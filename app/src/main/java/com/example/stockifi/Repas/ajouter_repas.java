package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ajouter_repas extends AppCompatActivity {
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_repas);

        Intent intent = getIntent();

        container = findViewById(R.id.container);
        ArrayList<String> quantity = intent.getStringArrayListExtra("stringListExtra");
        ArrayList<Produit> productList =intent.getParcelableArrayListExtra("selectedItems");
        System.out.println(productList);
        System.out.println(quantity);

        Spinner spinner = findViewById(R.id.spinner_options);

        String url = "http://10.0.2.2:1111/categorieDeProduits";
        List<String> itemList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                itemList.add(jsonObject.getString("intitule"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ajouter_repas.this, android.R.layout.simple_spinner_item, itemList);

// Specify the layout to use when the list of choices appears
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
                            spinner.setAdapter(adapter);






                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itemList.add("That didn't work!");


            }
        });

        queue.add(stringRequest);






        for(int i =0; i<productList.size();i++){
            // Create a new TextView
            TextView textView = new TextView(this);
            textView.setText(quantity.get(i)+ " " + productList.get(i).getUniteMesure() + " " + productList.get(i).getIntitule());
            textView.setTextColor(Color.WHITE); // Set text color to blue
            textView.setTextSize(20);

            // Set layout parameters for the TextView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT

            );
            textView.setLayoutParams(layoutParams);

            // Add the TextView to the LinearLayout
            container.addView(textView);
        }

        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}