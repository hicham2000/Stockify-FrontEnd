package com.example.stockifi.recettes;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecetteActivity extends AppCompatActivity {
    private Context context = this;
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

    private RecyclerView recyclerSimilaires;

    private TextView carbohydrateTextView;
    private TextView energieTextView;
    private TextView fibreTextView;
    private TextView lipideTextView;
    private TextView proteieTextView;
    private TextView sucreTextView;

    private List<RecetteModel> recettesSimilairesList;

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
        backendManager = new BackendManager(this);

        toolbarAppReccette = findViewById(R.id.toolbar_recette);
        bottomNavigationView = findViewById(R.id.androidx_window_recette);

        setSupportActionBar(toolbarAppReccette);

        recetteImageView = findViewById(R.id.recette_image_view);
        recetteNomTextView = findViewById(R.id.recette_nom_text_view);
        recetteIngredientsManquantsTextView = findViewById(R.id.recette_text_view_nombre_ingredients_manquants);
        portionMinusButton = findViewById(R.id.recette_Button_portion_minus);
        portionTextView = findViewById(R.id.recette_text_view_portion);
        portionPlusButton = findViewById(R.id.recette_Button_portion_plus);
        ingredientsDropdownButton = findViewById(R.id.recette_ingredients_dropdownButton);
        ingredientsListView = findViewById(R.id.recette_ingredients_listView);
        instructionsDePreparationDropdownButton = findViewById(R.id.recette_instructions_de_preparation_dropdownButton);
        instructionsDePreparationListView = findViewById(R.id.recette_instruction_de_preparation_listView);
        valeursNutritionnellesDropdownButton = findViewById(R.id.recette_valeurs_nutritionnelles_dropdownButton);
        valeursNutritionnellesLayout = findViewById(R.id.recette_valeurs_nutritionnelles_list);

        carbohydrateTextView = findViewById(R.id.text_view_recette_carbohydrate);
        energieTextView      = findViewById(R.id.text_view_recette_energie);
        fibreTextView        = findViewById(R.id.text_view_recette_fibre);
        lipideTextView       = findViewById(R.id.text_view_recette_lipide);
        proteieTextView      = findViewById(R.id.text_view_recette_protein);
        sucreTextView        = findViewById(R.id.text_view_recette_sucre);

        if (recetteIntent.hasExtra("Recette")) {
            recette = (RecetteModel) recetteIntent.getSerializableExtra("Recette");
            loadImageAsync(recetteImageView, recette.getImageUrl());
            recetteNomTextView.setText(recette.getRecetteName());
            portionTextView.setText(String.valueOf("1"));
            recetteIngredientsManquantsTextView.setText(String.valueOf(recette.getIngredientsMissing()) + " ingrédients manquants");

            List<RecetteModel.IngredientInfo> dataIngredients = recette.getIngredientsList();
            List<String> dataInstructions = recette.getInstructionDePreparation();
            RecetteModel.ValeurNutritionnel valeurNutritionnel = recette.getValeurNutritionnel();

            // Set click listeners
            portionMinusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int portion = Integer.parseInt(portionTextView.getText().toString());
                    if(portion <= 1) {
                        return;
                    }
                    portion -= 1;

                    double[] nutritionalValues = {
                            valeurNutritionnel.getCarbohydrate(),
                            valeurNutritionnel.getEnegie(),
                            valeurNutritionnel.getFibre(),
                            valeurNutritionnel.getLipide(),
                            valeurNutritionnel.getProteine(),
                            valeurNutritionnel.getSucre()
                    };

                    portionTextView.setText(String.valueOf(portion));
                    updateIngredientsManquants(portion);
                    updateNutritionalValues(nutritionalValues, portion);
                }
            });

            portionPlusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int portion = Integer.parseInt(portionTextView.getText().toString());
                    portion += 1;

                    double[] nutritionalValues = {
                            valeurNutritionnel.getCarbohydrate(),
                            valeurNutritionnel.getEnegie(),
                            valeurNutritionnel.getFibre(),
                            valeurNutritionnel.getLipide(),
                            valeurNutritionnel.getProteine(),
                            valeurNutritionnel.getSucre()
                    };

                    portionTextView.setText(String.valueOf(portion));
                    updateIngredientsManquants(portion);
                    updateNutritionalValues(nutritionalValues, portion);

                }
            });

            // Ingredients

            ingredientsListView.setVisibility(View.GONE);

            IngredientAdapter ingredientAdapter = new IngredientAdapter(this, dataIngredients);
            ingredientsListView.setAdapter(ingredientAdapter);

            ingredientsDropdownButton.setOnClickListener(view -> {
                int visibility = ingredientsListView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                ingredientsListView.setVisibility(visibility);

            });

            // Instructions de Preparation
            instructionsDePreparationListView.setVisibility(View.GONE);

            InstructionDePreparationAdapter instructionsDePreparationAdapter = new InstructionDePreparationAdapter(this, dataInstructions);
            instructionsDePreparationListView.setAdapter(instructionsDePreparationAdapter);

            instructionsDePreparationDropdownButton.setOnClickListener(view -> {
                int visibility = instructionsDePreparationListView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                instructionsDePreparationListView.setVisibility(visibility);
            });

            // Valeurs Nutritionnelles
            valeursNutritionnellesLayout.setVisibility(View.GONE);

            valeursNutritionnellesDropdownButton.setOnClickListener(view -> {
                int visibility = valeursNutritionnellesLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                valeursNutritionnellesLayout.setVisibility(visibility);
            });

            carbohydrateTextView.setText(String.valueOf(valeurNutritionnel.getCarbohydrate()));
            energieTextView.setText(String.valueOf(valeurNutritionnel.getEnegie()));
            fibreTextView.setText(String.valueOf(valeurNutritionnel.getFibre()));
            lipideTextView.setText(String.valueOf(valeurNutritionnel.getLipide()));
            proteieTextView.setText(String.valueOf(valeurNutritionnel.getProteine()));
            sucreTextView.setText(String.valueOf(valeurNutritionnel.getSucre()));


            // Initialize your RecyclerView
            recyclerSimilaires = findViewById(R.id.grid_recettes_similaires);

            try {
                loadData();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

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

    private void updateIngredientsManquants(int portion) {
        int nombreIngredientManquants = recette.getIngredientsMissing() * portion;
        recetteIngredientsManquantsTextView.setText(String.valueOf(nombreIngredientManquants) + " ingrédients manquants");
    }

    private void updateNutritionalValues(double[] nutritionalValues, int portion) {
        TextView[] valueTextViews = {
                carbohydrateTextView,
                energieTextView,
                fibreTextView,
                lipideTextView,
                proteieTextView,
                sucreTextView
        };

        for (int i = 0; i < nutritionalValues.length; i++) {
            double value = nutritionalValues[i] * portion;
            String formattedValue = formatDoubleValue(value);
            valueTextViews[i].setText(formattedValue);
        }
    }

    private String formatDoubleValue(double value) {
        return (value - (int) value == 0) ? String.valueOf((int) value) : String.valueOf(round(value, 2));
    }

    private double round(double val, int places) {
        if (places < 0) throw new IllegalArgumentException();
        if(places == 0) {
            return (double) Math.round(val);
        }
        int tmp = (int) Math.pow(10, places);
        return (double) ((double)Math.round(val * tmp) / tmp);
    }


    private void loadImageAsync(ImageView imageView, String imageUrl) {
        new GetImageFromUrl(imageView).execute(imageUrl);
    }

    private void loadData() throws JSONException {
        recettesSimilairesList = new ArrayList<>();
        int currentUser_id = 1;//myApp.getUser_id();
        long recetteId = recette.getId();

        backendManager.recupererRecettesSimilaires((long) currentUser_id, recetteId, new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                JSONArray recettesArray = response.getJSONArray("recettes");
                if (recettesArray.length() > 0) {
                    for(int i = 0; i < recettesArray.length(); i++){
                        JSONObject recetteObject = recettesArray.getJSONObject(i);
                        RecetteModel recette = new RecetteModel(recetteObject);
                        recettesSimilairesList.add(recette);
                    }

                    RecettesSimilairesAdapter recettesSimilairesAdapter = new RecettesSimilairesAdapter(context, recettesSimilairesList);;
                    recyclerSimilaires.setAdapter(recettesSimilairesAdapter);
                }

            }

            @Override
            public void onError(Exception error) {
                String errorMessage = "Error retrieving similares recipes: " + error.getMessage();
                Toast.makeText(RecetteActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
