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

import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.material.chip.Chip;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecettesRecommendeFiltreActivity extends AppCompatActivity {

    private SeekBar tempsDePreparationSeekBar;
    private TextView tempsDePreparationTextView;
    private AppCompatButton recetteButtonPortionPlus;
    private Chip produitSelectionneChip;
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

        tempsDePreparationSeekBar = findViewById(R.id.temps_de_preparation_seekBar);
        tempsDePreparationTextView = findViewById(R.id.temps_de_preparation_textView);
        recetteButtonPortionPlus = findViewById(R.id.recette_Button_portion_plus);
        produitSelectionneChip = findViewById(R.id.produit_selectionne_chip);
        annulerButton = findViewById(R.id.recette_recommende_filtre_annuler_button);
        appliquerButton = findViewById(R.id.recette_recommende_filtre_appliquer_button);

        // Add your logic and event listeners here
        // Example:
        tempsDePreparationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle seek bar progress change
                tempsDePreparationTextView.setText(progress + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle when user starts tracking touch on seek bar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle when user stops tracking touch on seek bar
            }
        });

        recetteButtonPortionPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle recette button portion plus click
            }
        });

        // Add event listeners for other buttons as needed

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window_recette_recommende_filtre);
        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(RecettesRecommendeFiltreActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });
    }
}
