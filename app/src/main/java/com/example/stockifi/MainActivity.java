package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private final String url = "http://10.0.2.2:1111/produits";
    private ArrayList<String> items = new ArrayList<>();
    private String[] itemss = {"hicham","ghj"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mylistview = findViewById(R.id.simpleListView);




    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView mylistview = findViewById(R.id.simpleListView);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject embedded = jsonResponse.getJSONObject("_embedded");
                            JSONArray produitsArray = embedded.getJSONArray("produits");
                            for (int i = 0; i < produitsArray.length(); i++) {
                                JSONObject product = produitsArray.getJSONObject(i);
                                String productName = product.getString("name");

                                System.out.println("Product Name: " + productName);
                                items.add(productName);
                            }

                            ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.simplelistv, items);
                            mylistview.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               items.add("That didn't work!");
                ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, R.layout.simplelistv, items);
                mylistview.setAdapter(adapter);

            }
        });

        queue.add(stringRequest);

        String[] stringArray = items.toArray(new String[0]);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simplelistv,stringArray);
        mylistview.setAdapter(adapter);
    }


}