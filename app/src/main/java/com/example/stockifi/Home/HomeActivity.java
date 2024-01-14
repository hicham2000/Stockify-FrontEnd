package com.example.stockifi.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.Gestion_Produit.AjouterProduit_ListeProduit;
import com.example.stockifi.Gestion_Produit.ListeProduit;
import com.example.stockifi.Liste_Course.AjouterProduit;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.MessageActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.Repas.ingredients;
import com.example.stockifi.budget.budgetActivity;
import com.example.stockifi.corbeille.corbeille;
import com.example.stockifi.databinding.ActivityHomeBinding;
import com.example.stockifi.recettes.RecettesRecommendeActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    String apiUrl = "";

    Button ajouterProduit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayout linearLayout = findViewById(R.id.list_Button);
        ImageView plusImageView = findViewById(R.id.plusImageView);
        ImageView xredImageView = findViewById(R.id.xImageView);


        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window);
        Menu navBar = bottomNavigationView.getMenu();

        MaterialToolbar toolbar = findViewById(R.id.toolbarStock);

        // Sélectionner l'élément "Courses"

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil1) {
                // L'utilisateur a cliqué sur "profil1"
                Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
                startActivity(intent);
                return true;
            }
            else if (item.getItemId() == R.id.message) {
                Intent profilIntent = new Intent(HomeActivity.this, MessageActivity.class);
                startActivity(profilIntent);
                return true;
            }
           else if (item.getItemId() == R.id.poubelle) {
                // L'utilisateur a cliqué sur "profil1"
                Intent intent = new Intent(HomeActivity.this, corbeille.class);
                startActivity(intent);
                return true;
            }
            return false;
        });



        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {

            Intent intent = new Intent(HomeActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();

            return true;
        });

        navBar.findItem(R.id.budget).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(HomeActivity.this, budgetActivity.class);
            startActivity(intent);
            finish();

            return true;
        });

        navBar.findItem(R.id.stock).setOnMenuItemClickListener(item -> {

            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(HomeActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();

            return true;
        });

        plusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rendre l'autre ImageView visible
                xredImageView.setVisibility(View.VISIBLE);
                plusImageView.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        xredImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xredImageView.setVisibility(View.INVISIBLE);
                plusImageView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        String[] itemsName = {"Mehdi", "Mehdi", "Mehdi", "Mehdi"};
        int[] image = {R.drawable.instagram, R.drawable.instagram, R.drawable.instagram, R.drawable.instagram};
        GridAdapter gridAdapter = new GridAdapter(HomeActivity.this, itemsName, image);
        binding.gridV.setAdapter(gridAdapter);

        binding.gridV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this, "you clicked on " + itemsName[position], Toast.LENGTH_SHORT).show();
            }
        });
        /*Button allButton = findViewById(R.id.all);
        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
                String url = "http://10.0.2.2:1111";
                JsonObjectRequest getProduits = new JsonObjectRequest(Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray produitsArray = response.getJSONArray("produit");
                                    for (int i = 0; i < produitsArray.length(); i++){
                                        JSONObject produitsObject = produitsArray.getJSONObject(i);
                                        // extract produit information

                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(getProduits);
            }
        });*/

        ajouterProduit=findViewById(R.id.button);
        ajouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(HomeActivity.this, ListeProduit.class);
                startActivity(intent);



            }
        });

        Button ajouterRepas = findViewById(R.id.button2);

        ajouterRepas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(HomeActivity.this, ingredients.class);
                startActivity(intent);



            }
        });

    }
    public void parseJson(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                // extract produit informations

            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchProduit() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
}