package com.example.stockifi.budget;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class budgetActivity extends AppCompatActivity {
    String apiUrl = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
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

