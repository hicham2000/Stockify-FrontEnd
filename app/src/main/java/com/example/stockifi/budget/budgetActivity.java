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

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.stockifi.R;
import com.example.stockifi.corbeille.corbeille;
import com.example.stockifi.recettes.RecettesRecommendeActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class budgetActivity extends AppCompatActivity {

    String apiUrl = "http://localhost:1111/stocks/1/budget";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_budget);


        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.poubelle) {
                Intent poubelleIntent = new Intent(budgetActivity.this, corbeille.class);
                startActivity(poubelleIntent);
           //     finish();
                return true;
            } else if (item.getItemId() == R.id.message) {
                // Handle the message item click
                // You can add your code here
                return true;
            } else if (item.getItemId() == R.id.profil1) {
                Intent profilIntent = new Intent(budgetActivity.this, ProfilActivity.class);
                startActivity(profilIntent);
             //   finish();
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
       //     finish();
            return true;
        });

        navBar.findItem(R.id.budget).setOnMenuItemClickListener(item -> {

            return true;
        });

        navBar.findItem(R.id.stock).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(budgetActivity.this, HomeActivity.class);
            startActivity(intent);
         //   finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(budgetActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);
           // finish();
            return true;
        });
    }
    public void fetchConsoamtion() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Double> budget = new ArrayList<>();
                // Assuming your response is a comma-separated string of doubles
                String[] values = response.split(",");

                for (String value : values) {
                    budget.add(Double.parseDouble(value));
                }
                updateUIWithBudgetData(budget);

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
    private void updateUIWithBudgetData(List<Double> budget) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Assuming budget has at least 5 values as per your server response
                if (budget.size() >= 5) {
                    // Find TextViews in your layout
                    TextView montantTotalTextView = findViewById(R.id.montant);
                    TextView montantConsommeTextView = findViewById(R.id.montant_consomme);
                    TextView montantGaspilleTextView = findViewById(R.id.montant_gaspille);
                    TextView pourcentageConsommeTextView = findViewById(R.id.pourcentage_consomme);
                    TextView pourcentageGaspilleTextView = findViewById(R.id.pourcentage_gaspille);

                    double totalBudget = budget.get(0);
                    double priceOfWastedProducts = budget.get(1);
                    double priceOfNonWastedProducts = budget.get(2);

                    // Update TextViews with formatted values
                    montantTotalTextView.setText(String.format(Locale.getDefault(), "%.2f", totalBudget));
                    montantConsommeTextView.setText(String.format(Locale.getDefault(), "%.2f", priceOfNonWastedProducts));
                    montantGaspilleTextView.setText(String.format(Locale.getDefault(), "%.2f", priceOfWastedProducts));

                    double percentageSpentOnWasted = (priceOfWastedProducts / totalBudget) * 100;
                    double percentageSpentOnNonWasted = (priceOfNonWastedProducts / totalBudget) * 100;

                    pourcentageConsommeTextView.setText(String.format(Locale.getDefault(), "%.2f", percentageSpentOnNonWasted));
                    pourcentageGaspilleTextView.setText(String.format(Locale.getDefault(), "%.2f", percentageSpentOnWasted));
                }
            }
        });
    }

}

