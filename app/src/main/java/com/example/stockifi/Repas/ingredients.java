package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ingredients extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        MyApp myApp = (MyApp) getApplication();
        int User_id = myApp.getUser_id();
        int User_Stock_id = myApp.getUser_stock_id();
        String url = "http://10.0.2.2:1111/stocks/"+User_Stock_id+"/products";

        ListView listView = findViewById(R.id.myListViewingredient);

        ArrayList<String> dataList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                String intitule = jsonObject.getString("intitule");
                                dataList.add(intitule);
                            }

                            CustomAdapter adapter = new CustomAdapter(ingredients.this, dataList);

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataList.add("That didn't work!");
                CustomAdapter adapter = new CustomAdapter(ingredients.this, dataList);

                listView.setAdapter(adapter);

            }
        });

        queue.add(stringRequest);





    }
}