package com.example.stockifi.recettes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.MessageActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.budget.budgetActivity;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecettesRecommendeActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private SearchView searchRecettesRecommendees;
    private Context context = this;
    private RecyclerView gridRecettesRecommende;
    private MaterialToolbar toolbarAppReccettesRecommende;
    private ImageView imageViewFilter;
    private Button btnTous;
    private Button btnFavoris;
    private Button btnFiltre;
    private BottomNavigationView bottomNavigationView;
    private ProgressBar loadingProgressBar;

    private MyApp myApp;

    private BackendManager backendManager;

    private List<RecetteModel> originalRecetteList = new ArrayList<RecetteModel>();
    private List<RecetteModel> favorisRecetteList = new ArrayList<RecetteModel>() ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.poubelle) {
            Intent poubelleIntent = new Intent(RecettesRecommendeActivity.this, corbeille.class);
            startActivity(poubelleIntent);
            finish();
            return true;
        } else if (itemId == R.id.message) {

                Intent profilIntent = new Intent(RecettesRecommendeActivity.this, MessageActivity.class);
                startActivity(profilIntent);

            return true;
        } else if (itemId == R.id.profil1) {
            Intent profilIntent = new Intent(RecettesRecommendeActivity.this, ProfilActivity.class);
            startActivity(profilIntent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_recommende);




        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window_recettes_recommende);


        // Sélectionner l'élément
        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.recette);
        menuItem.setChecked(true);

      //  int currentUser_id = 1;//myApp.getUser_id();

        myApp = (MyApp) getApplication();
        int currentUser_id = myApp.getUser_id();

        backendManager = new BackendManager(context);

        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        searchRecettesRecommendees = findViewById(R.id.search_recettes_recommende);

        toolbarAppReccettesRecommende = findViewById(R.id.toolbar_recettes_recommende);
        gridRecettesRecommende = findViewById(R.id.grid_recettes_recommende);
        imageViewFilter = findViewById(R.id.imageViewFilter);
        btnTous= findViewById(R.id.btnTous);
        btnFavoris = findViewById(R.id.btnFavoris);
        btnFiltre = findViewById(R.id.btnFiltre);

        bottomNavigationView = findViewById(R.id.androidx_window_recettes_recommende);

        // Set up the Toolbar
        setSupportActionBar(toolbarAppReccettesRecommende);

        // Set up the RecyclerView
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        gridRecettesRecommende.setLayoutManager(layoutManager);

        btnFavoris.setOnClickListener(v -> {
            RecettesAdapter recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
            gridRecettesRecommende.setAdapter(recettesAdapter);

            favorisRecetteList = new ArrayList<RecetteModel>();
            for (RecetteModel recette : originalRecetteList) {
                if (recette.isFavoris()) {
                    favorisRecetteList.add(recette); // Create a copy
                }
            }
            gridRecettesRecommende.setAdapter(recettesAdapter);
            recettesAdapter.setRecetteList(favorisRecetteList);
            recettesAdapter.notifyDataSetChanged();

            Log.d(TAG, "favorisRecetteList: " + favorisRecetteList.stream().map(RecetteModel::hashCode).collect(Collectors.toList()));

            favorisRecetteList = new ArrayList<RecetteModel>();
            btnFavoris.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFiltre.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        });

        btnTous.setOnClickListener(v -> {
            RecettesAdapter recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
            gridRecettesRecommende.setAdapter(recettesAdapter);

            recettesAdapter.setRecetteList(originalRecetteList);
            recettesAdapter.notifyDataSetChanged();

            Log.d(TAG, "originalRecetteList: " + originalRecetteList.stream().map(RecetteModel::hashCode).collect(Collectors.toList()));

            btnTous.setBackgroundResource(R.drawable.ellipse_menu);

            btnFavoris.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFiltre.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        });

        btnFiltre.setOnClickListener(v -> {

            btnFiltre.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFavoris.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

            Intent intent = new Intent(RecettesRecommendeActivity.this, RecettesRecommendeFiltreActivity.class);
            startActivity(intent);
        });

        imageViewFilter.setOnClickListener(v -> {

            btnFiltre.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFavoris.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

            Intent intent = new Intent(RecettesRecommendeActivity.this, RecettesRecommendeFiltreActivity.class);
            startActivity(intent);
        });

        //-------------------------
        searchRecettesRecommendees.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecettes(newText);
                return true;
            }
        });

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.budget).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, budgetActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.stock).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            return true;
        });

        try {
            loadData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void searchRecettes(String query) {
        List<RecetteModel> searchedRecettes = new ArrayList<>();
        if(query.isEmpty()) {
            searchedRecettes = originalRecetteList;
        } else {
            for (RecetteModel recette : originalRecetteList) {
                if (recette.getRecetteName().toLowerCase().contains(query.toLowerCase())) {
                    searchedRecettes.add(recette);
                }
            }
        }

        RecettesAdapter recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
        gridRecettesRecommende.setAdapter(recettesAdapter);
        recettesAdapter.setRecetteList(searchedRecettes);
        recettesAdapter.notifyDataSetChanged();
    }


    private void loadData() throws JSONException {
        originalRecetteList = new ArrayList<RecetteModel>();

        // Show the loading bar
        loadingProgressBar.setVisibility(View.VISIBLE);
        gridRecettesRecommende.setVisibility(View.GONE);

        myApp = (MyApp) getApplication();

        int currentUser_id = myApp.getUser_id();


        Intent intent = getIntent();
        if(intent.hasExtra("filterValuesJson")) {
            String filterValuesJson = (String) intent.getSerializableExtra("filterValuesJson");
            backendManager.recupererRecettesRecommendeesFiltrees(currentUser_id, filterValuesJson, new BackendManager.BackendResponseCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    JSONArray recettesArray = response.getJSONArray("recettes");
                    if (recettesArray.length() > 0) {
                        for(int i = 0; i < recettesArray.length(); i++){
                            JSONObject recetteObject = recettesArray.getJSONObject(i);
                            RecetteModel recette = new RecetteModel(recetteObject);
                            originalRecetteList.add(recette);
                        }

                        RecettesAdapter recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
                        gridRecettesRecommende.setAdapter(recettesAdapter);
                        recettesAdapter.setRecetteList(originalRecetteList);
                    }

                    loadingProgressBar.setVisibility(View.GONE);
                    gridRecettesRecommende.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(Exception error) {
                    error.printStackTrace();
                    loadingProgressBar.setVisibility(View.GONE);

                    if (error instanceof VolleyError) {
                        VolleyError volleyError = (VolleyError) error;

                        // Get the error response
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            try {
                                String errorResponse = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                                Log.e("ErrorResponse", errorResponse);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    String errorMessage = "Error retrieving recommended recipes: " + error.getMessage();
                    Toast.makeText(RecettesRecommendeActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            backendManager.recupererRecettesRecommendees(currentUser_id, new BackendManager.BackendResponseCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    JSONArray recettesArray = response.getJSONArray("recettes");
                    if (recettesArray.length() > 0) {
                        for(int i = 0; i < recettesArray.length(); i++){
                            JSONObject recetteObject = recettesArray.getJSONObject(i);
                            RecetteModel recette = new RecetteModel(recetteObject);
                            originalRecetteList.add(recette);
                        }

                        RecettesAdapter recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
                        gridRecettesRecommende.setAdapter(recettesAdapter);
                        recettesAdapter.setRecetteList(originalRecetteList);
                    }


                    loadingProgressBar.setVisibility(View.GONE);
                    gridRecettesRecommende.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(Exception error) {
                    loadingProgressBar.setVisibility(View.GONE);
                    String errorMessage = "Error retrieving recommended recipes: " + error.getMessage();
                    Toast.makeText(RecettesRecommendeActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
