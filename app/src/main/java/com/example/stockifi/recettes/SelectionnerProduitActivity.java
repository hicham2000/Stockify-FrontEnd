package com.example.stockifi.recettes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionnerProduitActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_SELECTION = 101;

    private ArrayList<String> produitsSelectionnesList = new ArrayList<String>();

    private MaterialToolbar toolbarAppReccette;
    private BottomNavigationView bottomNavigationView;

    private SearchView searchView;
    private RecyclerView recyclerView;
    private Button validerButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.poubelle) {
            Intent poubelleIntent = new Intent(this, corbeille.class);
            startActivity(poubelleIntent);
            finish();
            return true;
        } else if (itemId == R.id.message) {
            // Handle the message item click
            // You can add your code here
            return true;
        } else if (itemId == R.id.profil1) {
            Intent profilIntent = new Intent(this, ProfilActivity.class);
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
        setContentView(R.layout.activity_liste_produit_selectionner);

        toolbarAppReccette = findViewById(R.id.toolbar_liste_produit_selectionner);
        bottomNavigationView = findViewById(R.id.androidx_window_produit_selectionner);

        searchView = findViewById(R.id.search_produit_selectionner);
        recyclerView = findViewById(R.id.recycler_view_produit_selecionner);
        validerButton = findViewById(R.id.valider_produit_list_produit_selectionner);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        produitsSelectionnesList = new ArrayList<String>(Arrays.asList("Egg", "Tomato", "onions"));
        FiltreProduitsSelectionneAdapter filtreProduitsSelectionneAdapter = new FiltreProduitsSelectionneAdapter(produitsSelectionnesList);
        recyclerView.setAdapter(filtreProduitsSelectionneAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the query submission
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle the query text change
                // You can use this to filter your RecyclerView data
                return false;
            }
        });

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("ProduitsSelectionnes", produitsSelectionnesList);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        /* ------------------------------------------------------------------------------------------ */
        Menu appBar = toolbarAppReccette.getMenu();
        appBar.findItem(R.id.poubelle).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, corbeille.class);
            startActivity(intent);
            finish();
            return true;
        });

        appBar.findItem(R.id.message).setOnMenuItemClickListener(item -> {
            return true;
        });

        appBar.findItem(R.id.profil1).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        // Set up the BottomNavigationView

        Menu navBar = bottomNavigationView.getMenu();

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();

            return true;
        });
    }
}
