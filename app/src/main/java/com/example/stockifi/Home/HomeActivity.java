package com.example.stockifi.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
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
import com.example.stockifi.GlobalVariables.MyApp;
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

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    ArrayList<listData> listdata;
    ArrayList<listData2> listdata2;
    String apiUrl;
    String apiRepasUrl;

    Button ajouterProduit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        MyApp myApp = (MyApp) getApplication();
        int User_Stock_id = myApp.getUser_stock_id();



        apiUrl = "http://10.0.2.2:1111/stocks/"+User_Stock_id+"/products";
        apiRepasUrl = "http://10.0.2.2:1111/listRepas/repas/"+User_Stock_id;
        setContentView(binding.getRoot());
        listdata = new ArrayList<>();
        listdata2 = new ArrayList<>();
        LinearLayout linearLayout = findViewById(R.id.list_Button);
        ImageView plusImageView = findViewById(R.id.plusImageView);
        ImageView xredImageView = findViewById(R.id.xImageView);
        Button produit = findViewById(R.id.button4);
        Button repas = findViewById(R.id.button5);
        SearchView search_item = findViewById(R.id.search_item);


        search_item.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProduit(newText);
                searchPepas(newText);
                return true;
            }
        });

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
        produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchProduit();
            }
        });
        repas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRepas();
            }
        });
        String[] itemsName = {"Mehdi", "Mehdi", "Mehdi", "Mehdi"};
        int[] image = {R.drawable.instagram, R.drawable.instagram, R.drawable.instagram, R.drawable.instagram};

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
    private void searchProduit(String query){
        ArrayList<listData> produits = new ArrayList<>();
        if(query.isEmpty()) {
            produits = listdata;
        } else  {
            for (listData produit : listdata){
                if (produit.getIntitule().toLowerCase().contains(query.toLowerCase())) {
                    produits.add(produit);
                }
            }
            GridAdapter gridAdapter2 = new GridAdapter(HomeActivity.this, produits);
            binding.gridV.setAdapter(gridAdapter2);
        }
    }
    private void searchPepas(String query){
        ArrayList<listData2> repas = new ArrayList<>();
        if(query.isEmpty()) {
            repas = listdata2;
        } else  {
            for (listData2 repa : listdata2){
                if (repa.getIntitule().toLowerCase().contains(query.toLowerCase())) {
                    repas.add(repa);
                }
            }
            GridAdapter2 gridAdapter2 = new GridAdapter2(HomeActivity.this, repas);
            binding.gridV.setAdapter(gridAdapter2);
        }
    }
    public void parseJson(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            listdata.clear();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                // extract produit informations
                int id = object.getInt("id");
                String imageUrl = object.getString("imageUrl");
                String intitule = object.getString("intitule");
                String dateExpiration = object.getString("dateExpiration");
                System.out.println(object);

                listdata.add(new listData(id, intitule, dateExpiration,imageUrl));
            }
            GridAdapter gridAdapter = new GridAdapter(HomeActivity.this, listdata);
            binding.gridV.setAdapter(gridAdapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchProduit() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
        requestQueue.add(stringRequest);
    }
    public void parseJson2(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            listdata2.clear();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                // extract produit informations
                int id = object.getInt("id");
                String imageUrl = object.getString("imageUrl");
                String intitule = object.getString("intitule");
                String datePeremtion = object.getString("datePeremtion");

                listdata2.add(new listData2(id, intitule, datePeremtion,imageUrl));

            }
            GridAdapter2 gridAdapter2 = new GridAdapter2(HomeActivity.this, listdata2);
            binding.gridV.setAdapter(gridAdapter2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchRepas() {
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, apiRepasUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson2(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue1.add(stringRequest1);
    }
}