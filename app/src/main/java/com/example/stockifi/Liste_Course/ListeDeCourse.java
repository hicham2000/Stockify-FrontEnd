package com.example.stockifi.Liste_Course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.search.SearchView;

public class ListeDeCourse extends AppCompatActivity {

    Button AjouterBouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_liste_de_course);
        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window_course);


        // Sélectionner l'élément "Courses"
        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.courses);
        menuItem.setChecked(true);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_liste_course);

        // Gestionnaire de clic pour l'élément "profil1"
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.profil1) {
                // L'utilisateur a cliqué sur "profil1"
                Intent intent = new Intent(ListeDeCourse.this, ProfilActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        ImageView poubelleImage = findViewById(R.id.poubelle_image);

        poubelleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur l'image
                Intent intent = new Intent(ListeDeCourse.this, ModifierProduit.class);
                startActivity(intent);
            }
        });

        AjouterBouton = findViewById(R.id.ajouter_produit);

        AjouterBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(ListeDeCourse.this, AjouterProduit.class);
                startActivity(intent);



            }
        });

    }
}