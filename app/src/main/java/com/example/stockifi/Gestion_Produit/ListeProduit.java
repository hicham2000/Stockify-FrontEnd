package com.example.stockifi.Gestion_Produit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        Button nouveau=findViewById(R.id.ajouter_produit_Liste_Produit);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_listeproduit);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

      //  list_produit=findViewById(R.id.myListViewProduit);

        //ArrayList<ProduitALaListe> objets=new ArrayList<>();
      //  objets.add(new ProduitALaListe(R.drawable.profil,"lait"));
      //  ListeproduitAdapter adapter=new ListeproduitAdapter(getApplicationContext(),R.layout.liste_produit,objets);
    //    list_produit.setAdapter(adapter);


        ListView listView = findViewById(R.id.myListViewProduit);

        ArrayList<ProduitALaListe> dataList = new ArrayList<>();

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
                                Long id=jsonObject.getLong("id");
                                String intitule = jsonObject.getString("intitule");
                                String imageUrl = jsonObject.getString("imageUrl");
                                String mesure = jsonObject.getString("uniteDeMesure");

                                dataList.add(new ProduitALaListe(id,imageUrl,intitule,mesure));
                            }

                            ListeproduitAdapter adapter = new ListeproduitAdapter(ListeProduit.this, dataList);
                       //     adapter.notifyDataSetChanged();



                            listView.setAdapter(adapter);




                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   dataList.add("That didn't work!");
                ListeproduitAdapter adapter = new ListeproduitAdapter(ListeProduit.this, dataList);

                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

            }

        });

        queue.add(stringRequest);


        nouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Action à effectuer lors du clic sur le bouton
                Intent intent = new Intent(ListeProduit.this, AjouterProduit_ListeProduit.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sender", "New");
                startActivity(intent);
            }
        });


    }


}