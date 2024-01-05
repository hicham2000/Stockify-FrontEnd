package com.example.stockifi.recettes;

import static androidx.test.InstrumentationRegistry.getContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.LoginActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.recettes.RecettesAdapter;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecettesRecommendeActivity extends AppCompatActivity {

    private RecettesAdapter recettesAdapter;

    private Context context = this;
    private RecyclerView gridRecettesRecommende;
    private MaterialToolbar toolbarAppReccettesRecommende;
    private ImageView imageViewFilter;
    private Button btnTous;
    private Button btnFavoris;
    private Button btnFiltre;
    private BottomNavigationView bottomNavigationView;
    private ProgressBar loadingProgressBar;

    private MyApp myApp = (MyApp) getApplication();

    private BackendManager backendManager;

    private List<RecetteModel> originalRecetteList = new ArrayList<RecetteModel>(); // Store the original list of recipes
    private List<RecetteModel> currentRecetteList; // Store the current list of recipes displayed

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
            // Handle the message item click
            // You can add your code here
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

        int currentUser_id = 1;//myApp.getUser_id();
        backendManager = new BackendManager(context);

        loadingProgressBar = findViewById(R.id.loadingProgressBar);

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

        loadData();

        btnFavoris.setOnClickListener(v -> {
            // Filter the list to show only recipes with isFavoris == true
            List<RecetteModel> favorisList = new ArrayList<>();
            for (RecetteModel recette : originalRecetteList) {
                if (recette.isFavoris()) {
                    favorisList.add(recette); // Create a copy
                }
            }

            // Update the RecyclerView with the filtered list
            currentRecetteList = new ArrayList<>(favorisList);
            recettesAdapter.setRecetteList(currentRecetteList);
            recettesAdapter.notifyDataSetChanged();

            btnFavoris.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFiltre.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        });

        btnTous.setOnClickListener(v -> {
            // Update the RecyclerView with the original unfiltered list
            currentRecetteList = new ArrayList<>(originalRecetteList);

            recettesAdapter.setRecetteList(currentRecetteList);
            recettesAdapter.notifyDataSetChanged();

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


        //-------------------------

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            return true;
        });

    }

    private void loadData() {
        // Show the loading bar
        loadingProgressBar.setVisibility(View.VISIBLE);

        int currentUser_id = 1;//myApp.getUser_id();

        // Perform data retrieval
        backendManager.recupererRecettesRecommendees(currentUser_id, new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                loadingProgressBar.setVisibility(View.GONE);
                JSONArray recettesArray = response.getJSONArray("recettes");
                if (recettesArray.length() > 0) {
                    for(int i = 0; i < recettesArray.length(); i++){
                        JSONObject recetteObject = recettesArray.getJSONObject(i);
                        RecetteModel recette = new RecetteModel(recetteObject);
                        originalRecetteList.add(recette);
                    }
                }

                recettesAdapter = new RecettesAdapter(context, originalRecetteList, backendManager, myApp);
                gridRecettesRecommende.setAdapter(recettesAdapter);
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
