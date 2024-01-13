package com.example.stockifi.Repas;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class ViewRepas extends AppCompatActivity {
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_repas);
        EditText editText = findViewById(R.id.editTexte_t);
        EditText editTextt = findViewById(R.id.cat);
        editText.setEnabled(false);
        editTextt.setEnabled(false);
        Button buttonAnnuler = findViewById(R.id.button_supprimer);
        buttonAnnuler.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

        int repasId = 2;
        TextView name = findViewById(R.id.editTexte_t);
        TextView cat = findViewById(R.id.cat);
        TextView alert = findViewById(R.id.alertt);
        TextView per = findViewById(R.id.peremtiontext);
        container = findViewById(R.id.container);
        String url1 = "http://10.0.2.2:1111/api/Ingredients/"+repasId;
        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                String name = jsonObject.getString("intitule");
                                String unit = jsonObject.getString("uniteDeMesure");
                                Double qu = jsonObject.getDouble("quantity");

                                TextView textView = new TextView(ViewRepas.this);
                                textView.setText(qu+ " " + unit + " " + name);
                                textView.setTextColor(Color.WHITE); // Set text color to blue
                                textView.setTextSize(20);

                                // Set layout parameters for the TextView
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT

                                );
                                textView.setLayoutParams(layoutParams);

                                // Add the TextView to the LinearLayout
                                container.addView(textView);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hhhhhhhhhcxvxcvxcvx");

            }
        });

        queue1.add(stringRequest1);


        String url = "http://10.0.2.2:1111/stocks/repasbyid/"+repasId;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            System.out.println(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
                            System.out.println("hhh"+ingredientsArray);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            String intituleValue = jsonObject.getString("intitule");
                            String categories = jsonObject.getString("categories");
                            String al = jsonObject.getString("dateAlert");
                            String pero = jsonObject.getString("datePeremtion");
                            name.setText(intituleValue);
                            cat.setText(categories);
                            alert.setText(al.split("T")[0]);
                            per.setText(pero.split("T")[0]);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hhhhhhhhhcxvxcvxcvx");

            }
        });

        queue.add(stringRequest);


    }
}