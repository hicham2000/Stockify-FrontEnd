package com.example.stockifi.budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.example.stockifi.recettes.RecettesRecommendeActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class budgetActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_budget);


        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.poubelle) {
                Intent poubelleIntent = new Intent(budgetActivity.this, corbeille.class);
                startActivity(poubelleIntent);
                finish();
                return true;
            } else if (item.getItemId() == R.id.message) {
                // Handle the message item click
                // You can add your code here
                return true;
            } else if (item.getItemId() == R.id.profil1) {
                Intent profilIntent = new Intent(budgetActivity.this, ProfilActivity.class);
                startActivity(profilIntent);
                finish();
                return true;
            }
            return false;
        });

       BottomNavigationView bottomNavigationView=findViewById(R.id.androidx_window_budget);
        Menu navBar = bottomNavigationView.getMenu();

        MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.budget);
        menuItem.setChecked(true);

        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(budgetActivity.this, ListeDeCourse.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.budget).setOnMenuItemClickListener(item -> {

            return true;
        });

        navBar.findItem(R.id.stock).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(budgetActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(budgetActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();
            return true;
        });
    }
}
