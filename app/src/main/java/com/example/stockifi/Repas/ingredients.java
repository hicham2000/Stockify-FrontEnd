package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

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
    public ArrayList<Produit> dataList;
    SearchView search_listeCourse;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ingredient);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        MyApp myApp = (MyApp) getApplication();
        int User_id = myApp.getUser_id();
        int User_Stock_id = myApp.getUser_stock_id();
        String url = "http://10.0.2.2:1111/stocks/"+User_Stock_id+"/products";

        listView = findViewById(R.id.myListViewingredient);

        dataList = new ArrayList<>();

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

                            final CustomAdapter[] adapter = {new CustomAdapter(ingredients.this, dataList)};

                            listView.setAdapter(adapter[0]);

                            Button nextButton = findViewById(R.id.ajouteringredients);


                            nextButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayList<Produit> checked = adapter[0].getCheckedPositions();



                                    if (!checked.isEmpty()) {
                                        Intent intent = new Intent(ingredients.this, ingredients_quantity.class);
                                      intent.putParcelableArrayListExtra("selectedItems", checked);
                                      //  System.out.println(checked);
                                        startActivity(intent);
                                    }
                                }
                            });


                            search_listeCourse = findViewById(R.id.searchingredient);

                            search_listeCourse.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String s) {
                                    String produit = search_listeCourse.getQuery().toString();
                                    // Handle the submitted query
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    ArrayList<Produit> checked = adapter[0].getCheckedPositions();





                                    String produit = search_listeCourse.getQuery().toString();
                                  //  System.out.println(produit);
                                    ArrayList<Produit> p = new ArrayList<>();

                                    dataList.stream()
                                            .filter(a -> a.getIntitule().toLowerCase().contains(produit.toLowerCase()))
                                            .forEach(p::add);
                                    adapter[0] = new CustomAdapter(ingredients.this, p);
                                    ListView listView = findViewById(R.id.myListViewingredient);
                                    listView.setAdapter(adapter[0]);
                                    adapter[0].setRadioButtonsToFalseExceptABC(checked);



                                    return false;
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

    @Override
    protected void onStart() {
        super.onStart();

    }


}


