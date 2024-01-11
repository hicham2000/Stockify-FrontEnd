package com.example.stockifi.recettes;

import static com.example.stockifi.recettes.SelectionnerProduitActivity.REQUEST_CODE_SELECTION;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecettesRecommendeFiltreActivity extends AppCompatActivity {

    public Context context = this;

    private List<String> produitsSelectionnesList = new ArrayList<String>();
    private MaterialToolbar toolbarAppReccette;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView regimeSpeciauxRecyclerView;
    private SeekBar tempsDePreparationSeekBar;
    private TextView tempsDePreparationTextView;
    private AppCompatButton recetteButtonAjouterProduit;
    private RecyclerView produitsSelectionneRecyclerView;
    private Button annulerButton;
    private Button appliquerButton;

    private FilterValues filterValues;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.poubelle) {
            Intent poubelleIntent = new Intent(RecettesRecommendeFiltreActivity.this, corbeille.class);
            startActivity(poubelleIntent);
            finish();
            return true;
        } else if (itemId == R.id.message) {
            // Handle the message item click
            // You can add your code here
            return true;
        } else if (itemId == R.id.profil1) {
            Intent profilIntent = new Intent(RecettesRecommendeFiltreActivity.this, ProfilActivity.class);
            startActivity(profilIntent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_recommende_filtre);

        toolbarAppReccette = findViewById(R.id.toolbar_recette);
        bottomNavigationView = findViewById(R.id.androidx_window_recette_recommende_filtre);

        tempsDePreparationSeekBar = findViewById(R.id.temps_de_preparation_seekBar);
        tempsDePreparationTextView = findViewById(R.id.temps_de_preparation_textView);
        recetteButtonAjouterProduit = findViewById(R.id.recette_Button_add_produit);
        produitsSelectionneRecyclerView = findViewById(R.id.produits_selectionne);
        annulerButton = findViewById(R.id.recette_recommende_filtre_annuler_button);
        appliquerButton = findViewById(R.id.recette_recommende_filtre_appliquer_button);
        regimeSpeciauxRecyclerView = findViewById(R.id.regime_speciaux_recycler_view);

        // Set up the RecyclerView
        regimeSpeciauxRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        List<String> regimeList = new ArrayList<String>(Arrays.asList("Vegetable", "Sans Lactose", "Sans Porc", "Sans Alcool"));
        RegimeSpeciauxAdapter regimeSpeciauxadapter = new RegimeSpeciauxAdapter(regimeList);
        regimeSpeciauxRecyclerView.setAdapter(regimeSpeciauxadapter);

        // Add your logic and event listeners here
        // Example:
        tempsDePreparationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle seek bar progress change
                tempsDePreparationTextView.setText(String.valueOf(progress) + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle when user starts tracking touch on seek bar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle when the user stops tracking touch on the seek bar
            }
        });

        recetteButtonAjouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectionnerProduitActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECTION);

            }
        });

        // Assuming produitsSelectionneRecyclerView is your RecyclerView
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        produitsSelectionneRecyclerView.setLayoutManager(flexboxLayoutManager);


        ProduitsSelectionneAdapter produitsSelectionneAdapter = new ProduitsSelectionneAdapter(produitsSelectionnesList);
        produitsSelectionneRecyclerView.setAdapter(produitsSelectionneAdapter);


        appliquerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterValues = new FilterValues();

                filterValues.setSelectedRegimes(regimeSpeciauxadapter.getSelectedRegimes());
                filterValues.setTempsDePreparation(tempsDePreparationSeekBar.getProgress());
                filterValues.setSelectedProduits(produitsSelectionneAdapter.getSelectedProduits());

                Gson gson = new Gson();
                String filterValuesJson = gson.toJson(filterValues);

                Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, RecettesRecommendeActivity.class);
                intent.putExtra("filterValuesJson", filterValuesJson);
                startActivity(intent);

            }
        });

        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regimeSpeciauxadapter.setClickCount(0);
                regimeSpeciauxadapter.setSelectedRegimes(new ArrayList<String>());
                regimeSpeciauxadapter.notifyDataSetChanged();

                produitsSelectionneAdapter.setSelectedProduits(new ArrayList<String>());
                produitsSelectionneAdapter.notifyDataSetChanged();

            }
        });

        /* ------------------------------------------------------------------------------------------ */
        Menu appBar = toolbarAppReccette.getMenu();
        appBar.findItem(R.id.poubelle).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, corbeille.class);
            startActivity(intent);
            finish();
            return true;
        });

        appBar.findItem(R.id.message).setOnMenuItemClickListener(item -> {
            return true;
        });

        appBar.findItem(R.id.profil1).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, ProfilActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();

            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECTION && resultCode == Activity.RESULT_OK) {
            assert data != null;
            produitsSelectionnesList = data.getStringArrayListExtra("ProduitsSelectionnes");
            ProduitsSelectionneAdapter produitsSelectionneAdapter = new ProduitsSelectionneAdapter(produitsSelectionnesList);
            produitsSelectionneRecyclerView.setAdapter(produitsSelectionneAdapter);
        }
    }

}
