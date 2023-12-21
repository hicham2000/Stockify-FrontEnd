package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ingredients extends AppCompatActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        MyApp myApp = (MyApp) getApplication();
        int User_id = myApp.getUser_id();
        int User_Stock_id = myApp.getUser_stock_id();
        String url = "http://10.0.2.2:1111/stocks/"+User_Stock_id+"/products";

        ListView listView = findViewById(R.id.myListViewingredient);

        ArrayList<Produit> dataList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                Produit produit = new Produit(jsonObject.getInt("id"));
                                produit.setIntitule(jsonObject.getString("intitule"));
                                produit.setQuantite(jsonObject.getDouble("quantite"));
                                produit.setUniteMesure(jsonObject.getString("uniteDeMesure"));
                                System.out.println(produit);
                                String intitule = jsonObject.getString("intitule");
                                dataList.add(produit);
                            }

                            CustomAdapter adapter = new CustomAdapter(ingredients.this, dataList);

                            listView.setAdapter(adapter);
                            Button nextButton = findViewById(R.id.ajouteringredients);


                            nextButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayList<Produit> checked = adapter.getCheckedPositions();



                                    if (!checked.isEmpty()) {
                                        Intent intent = new Intent(ingredients.this, ingredients_quantity.class);
                                      intent.putParcelableArrayListExtra("selectedItems", checked);
                                      //  System.out.println(checked);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dataList.add("That didn't work!");
                CustomAdapter adapter = new CustomAdapter(ingredients.this, dataList);

                listView.setAdapter(adapter);

            }
        });

        queue.add(stringRequest);





    }


}