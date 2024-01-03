package com.example.stockifi.recettes;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecettesRecommendeFiltreActivity extends AppCompatActivity {

    private MaterialToolbar toolbarAppReccette;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView regimeSpeciauxRecyclerView;
    private SeekBar tempsDePreparationSeekBar;
    private TextView tempsDePreparationTextView;
    private AppCompatButton recetteButtonPortionPlus;
    private RecyclerView produitsSelectionneRecyclerView;
    private Button annulerButton;
    private Button appliquerButton;

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
        recetteButtonPortionPlus = findViewById(R.id.recette_Button_portion_plus);
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

        recetteButtonPortionPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle recette button portion plus click
            }
        });

        // Assuming produitsSelectionneRecyclerView is your RecyclerView
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);

        produitsSelectionneRecyclerView.setLayoutManager(flexboxLayoutManager);

        List<String> produitsSelectionnesList = new ArrayList<String>(Arrays.asList("Egg", "Tomato", "onions"));
        ProduitsSelectionneAdapter produitsSelectionneAdapter = new ProduitsSelectionneAdapter(produitsSelectionnesList);
        produitsSelectionneRecyclerView.setAdapter(produitsSelectionneAdapter);

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
}
