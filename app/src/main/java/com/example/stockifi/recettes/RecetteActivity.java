package com.example.stockifi.recettes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecetteActivity extends AppCompatActivity {
    private MaterialToolbar toolbarAppReccette;
    private BottomNavigationView bottomNavigationView;

    private ImageView recetteImageView;
    private TextView recetteNomTextView;
    private TextView recetteIngredientsManquantsTextView;
    private Button portionMinusButton;
    private TextView portionTextView;
    private Button portionPlusButton;

    private Button ingredientsDropdownButton;
    private Button instructionsDePreparationDropdownButton;
    private Button valeursNutritionnellesDropdownButton;

    private LinearLayout valeursNutritionnellesLayout;
    private RecyclerView ingredientsListView;
    private RecyclerView instructionsDePreparationListView;

    private IngredientAdapter ingredientsAdapter;
    private InstructionDePreparationAdapter instructionsDePreparationAdapter ;
    private ArrayAdapter<String> valeursNutritionnellesAdapter;

    private RecettesSimilairesAdapter recettesSimilairesAdapter;
    private RecyclerView recyclerSimilaires;

    private RecetteModel recette;
    private MyApp myApp = (MyApp) getApplication();

    private BackendManager backendManager;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.poubelle) {
            Intent poubelleIntent = new Intent(RecetteActivity.this, corbeille.class);
            startActivity(poubelleIntent);
            finish();
            return true;
        } else if (itemId == R.id.message) {
            // Handle the message item click
            // You can add your code here
            return true;
        } else if (itemId == R.id.profil1) {
            Intent profilIntent = new Intent(RecetteActivity.this, ProfilActivity.class);
            startActivity(profilIntent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        Intent recetteIntent = getIntent();

        int currentUser_id = 1;//myApp.getUser_id();
        //backendManager = new BackendManager(this);

        toolbarAppReccette = findViewById(R.id.toolbar_recette);
        bottomNavigationView = findViewById(R.id.androidx_window_recette);

        setSupportActionBar(toolbarAppReccette);

        recetteImageView = findViewById(R.id.recette_image_view);
        recetteNomTextView = findViewById(R.id.recette_nom_text_view);
        recetteIngredientsManquantsTextView = findViewById(R.id.recette_text_view_nombre_ingredients_manquants);
        portionMinusButton = findViewById(R.id.recette_Button_portion_minus);
        portionTextView = findViewById(R.id.recette_text_view_portion);
        portionPlusButton = findViewById(R.id.recette_Button_portion_plus);

        if (recetteIntent.hasExtra("Recette")) {
            recette = (RecetteModel) recetteIntent.getSerializableExtra("Recette");
            assert recette != null;
            loadImageAsync(recetteImageView, recette.getImageUrl());
            recetteNomTextView.setText(recette.getRecetteName());
            recetteIngredientsManquantsTextView.setText(recette.getIngredientsMissing() + " ingrédients manquants");
        }

        // Set click listeners
        portionMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle portion minus button click
            }
        });

        portionPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle portion plus button click
            }
        });

        // Ingredients
        ingredientsDropdownButton = findViewById(R.id.recette_ingredients_dropdownButton);
        ingredientsListView = findViewById(R.id.recette_ingredients_listView);
        ingredientsListView.setVisibility(View.GONE);

        String[] dataIngredients = {"4 blueberries", "0.25 granulated sugar", "1 vanilla yogurt", "1 lemon juice"};
        ArrayList<String> ingredientsList = new ArrayList<>(Arrays.asList(dataIngredients));
        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, ingredientsList);
        ingredientsListView.setAdapter(ingredientAdapter);

        ingredientsDropdownButton.setOnClickListener(view -> {
            int visibility = ingredientsListView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            ingredientsListView.setVisibility(visibility);

        });

        // Instructions de Preparation
        instructionsDePreparationDropdownButton = findViewById(R.id.recette_instructions_de_preparation_dropdownButton);
        instructionsDePreparationListView = findViewById(R.id.recette_instruction_de_preparation_listView);
        instructionsDePreparationListView.setVisibility(View.GONE);

        String[] dataInstructions = {
                "Toss 2 cups berries with sugar", "Let stand for 45 minutes", "stirring occasionally",
                "Transfer berry-sugar mixture to food processor", "Add yogurt and process until smooth", "Strain through fine sieve"};
        ArrayList<String> instructionsList = new ArrayList<>(Arrays.asList(dataInstructions));
        InstructionDePreparationAdapter instructionsDePreparationAdapter = new InstructionDePreparationAdapter(this, instructionsList);
        instructionsDePreparationListView.setAdapter(instructionsDePreparationAdapter);

        instructionsDePreparationDropdownButton.setOnClickListener(view -> {
            int visibility = instructionsDePreparationListView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            instructionsDePreparationListView.setVisibility(visibility);
        });

        // Valeurs Nutritionnelles
        valeursNutritionnellesDropdownButton = findViewById(R.id.recette_valeurs_nutritionnelles_dropdownButton);
        valeursNutritionnellesLayout = findViewById(R.id.recette_valeurs_nutritionnelles_list);
        valeursNutritionnellesLayout.setVisibility(View.GONE);

        valeursNutritionnellesDropdownButton.setOnClickListener(view -> {
            int visibility = valeursNutritionnellesLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            valeursNutritionnellesLayout.setVisibility(visibility);
        });


        //Recette Similaires, Recycler view
        /*List<RecetteModel> recetteSimilairesList = new ArrayList<RecetteModel>();

        recetteSimilairesList.add(new RecetteModel(
                "https://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/38/YUeirxMLQaeE1h3v3qnM_229%20berry%20blue%20frzn%20dess.jpg",
                "Low-Fat Berry Blue Frozen Dessert",
                1485,
                25,
                false));


        recetteSimilairesList.add(new RecetteModel(
                "https://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/39/picM9Mhnw.jpg",
                "Biryani",
                265,
                1,
                true));

        recetteSimilairesList.add(new RecetteModel(
                "https://img.sndimg.com/food/image/upload/w_555,h_416,c_fit,fl_progressive,q_95/v1/img/recipes/38/YUeirxMLQaeE1h3v3qnM_229%20berry%20blue%20frzn%20dess.jpg",
                "Low-Fat Berry Blue Frozen Dessert",
                1485,
                25,
                false));

        // Initialize your RecyclerView
        recyclerSimilaires = findViewById(R.id.grid_recettes_similaires);

        // Initialize the adapter
        recettesSimilairesAdapter = new RecettesSimilairesAdapter(this, recetteSimilairesList);

        // Set the adapter to your RecyclerView
        recyclerSimilaires.setAdapter(recettesSimilairesAdapter);*/


        /* ------------------------------------------------------------------------------------------ */
        Menu appBar = toolbarAppReccette.getMenu();
        appBar.findItem(R.id.poubelle).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecetteActivity.this, corbeille.class);
            startActivity(intent);
            finish();
            return true;
        });

        appBar.findItem(R.id.message).setOnMenuItemClickListener(item -> {
            return true;
        });

        appBar.findItem(R.id.profil1).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecetteActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecetteActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecetteActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();

            return true;
        });
    }


    private void loadImageAsync(ImageView imageView, String imageUrl) {
        new GetImageFromUrl(imageView).execute(imageUrl);
    }
}
