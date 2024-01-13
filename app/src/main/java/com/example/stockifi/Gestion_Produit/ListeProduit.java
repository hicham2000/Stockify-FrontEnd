package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.Liste_Course.ListeCourseAdapter;
import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListeProduit extends AppCompatActivity {

    ListView list_produit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);

        list_produit=findViewById(R.id.myListViewProduit);

        ArrayList<ProduitALaListe> objets=new ArrayList<>();
        objets.add(new ProduitALaListe(R.drawable.profil,"lait"));
        ListeproduitAdapter adapter=new ListeproduitAdapter(getApplicationContext(),R.layout.liste_produit,objets);
        list_produit.setAdapter(adapter);


        ListView listView = findViewById(R.id.myListViewProduit);

        ArrayList<Produit> dataList = new ArrayList<>();

        String url = "http://10.0.2.2:1111/globals";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                int id=jsonObject.getInt("id");
                                String intitule = jsonObject.getString("intitule");
                                String imageUrl = jsonObject.getString("imageUrl");
                                String mesure = jsonObject.getString("uniteDeMesure");

                                dataList.add(new Produit(id,intitule,imageUrl,mesure,check));
                            }

                            ListeCourseAdapter adapter = new ListeCourseAdapter(ListeProduit.this, dataList);
                            adapter.notifyDataSetChanged();



                            listView.setAdapter(adapter);




                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   dataList.add("That didn't work!");
                ListeCourseAdapter adapter = new ListeCourseAdapter(ListeProduit.this, dataList);

                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

            }

        });

        queue.add(stringRequest);


    }
}