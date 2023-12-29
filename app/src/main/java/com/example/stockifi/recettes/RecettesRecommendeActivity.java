package com.example.stockifi.recettes;

import static androidx.test.InstrumentationRegistry.getContext;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RecettesRecommendeActivity extends AppCompatActivity {

    private RecyclerView gridRecettesRecommende;
    private MaterialToolbar toolbarAppReccettesRecommende;
    private ImageView imageViewFilter;
    private Button btnTous;
    private Button btnFavoris;
    private Button btnFiltre;
    private BottomNavigationView bottomNavigationView;

    private MyApp myApp = (MyApp) getApplication();

    private BackendManager backendManager;

    private List<RecetteModel> originalRecetteList; // Store the original list of recipes
    private List<RecetteModel> currentRecetteList; // Store the current list of recipes displayed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_recommende);

        int currentUser_id = 1;//myApp.getUser_id();
        //backendManager = new BackendManager(this);

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

        List<RecetteModel> recetteList = new ArrayList<RecetteModel>();

        recetteList.add(new RecetteModel(
                "https://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/38/YUeirxMLQaeE1h3v3qnM_229%20berry%20blue%20frzn%20dess.jpg",
                "Low-Fat Berry Blue Frozen Dessert",
                1485,
                25,
                false));


        recetteList.add(new RecetteModel(
                "https://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/39/picM9Mhnw.jpg",
                "Biryani",
                265,
                1,
                true));

        originalRecetteList = new ArrayList<>(recetteList);
        currentRecetteList = new ArrayList<>(originalRecetteList);

        RecettesAdapter recettesAdapter = new RecettesAdapter(this, recetteList);
        gridRecettesRecommende.setAdapter(recettesAdapter);

        btnFavoris.setOnClickListener(v -> {
            // Filter the list to show only recipes with isFavoris == true
            List<RecetteModel> favorisList = new ArrayList<>();
            for (RecetteModel recette : originalRecetteList) {
                if (recette.isFavoris()) {
                    favorisList.add(new RecetteModel(recette)); // Create a copy
                }
            }

            // Update the RecyclerView with the filtered list
            currentRecetteList = new ArrayList<>(favorisList);
            recettesAdapter.setRecetteList(currentRecetteList);
            recettesAdapter.notifyDataSetChanged();
            System.out.println("Favoris button: currentRecetteList => " + currentRecetteList);

            btnFavoris.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFiltre.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

        });

        btnTous.setOnClickListener(v -> {
            // Update the RecyclerView with the original unfiltered list
            currentRecetteList = new ArrayList<>(originalRecetteList);
            recettesAdapter.setRecetteList(currentRecetteList);
            recettesAdapter.notifyDataSetChanged();
            System.out.println("Tous button : currentRecetteList => "+ currentRecetteList);

            btnTous.setBackgroundResource(R.drawable.ellipse_menu);

            btnFavoris.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFiltre.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        });

        btnFiltre.setOnClickListener(v -> {

            btnFiltre.setBackgroundResource(R.drawable.ellipse_menu);

            btnTous.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            btnFavoris.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        });




        //-------------------------

        Menu appBar = toolbarAppReccettesRecommende.getMenu();
        appBar.findItem(R.id.poubelle).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, corbeille.class);
            startActivity(intent);

            return true;
        });

        appBar.findItem(R.id.message).setOnMenuItemClickListener(item -> {

            return true;
        });

        appBar.findItem(R.id.profil1).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, ProfilActivity.class);
            startActivity(intent);
            return true;
        });

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, ListeDeCourse.class);
            startActivity(intent);

            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);

            return true;
        });

    }
}
