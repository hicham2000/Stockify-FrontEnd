package com.example.stockifi.Liste_Course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.MessageActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.budget.budgetActivity;
import com.example.stockifi.corbeille.corbeille;
import com.example.stockifi.recettes.RecettesRecommendeActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ListeDeCourse extends AppCompatActivity {

   // private static final String BASE_URL = "192.168.11.100:1111";
    private static final String BASE_URL = "10.0.2.2:1111";
    ArrayList<Produit> dataList = new ArrayList<>();
    ListeCourseAdapter adapter;

    Button AjouterBouton;
    SearchView search_listeCourse;
    LinearLayout ViderListeCourse;
  //  ImageView poubelleImage = findViewById(R.id.poubelle_image);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_de_course);
        // Obtenez une référence à la barre d'outils
        MaterialToolbar toolbar = findViewById(R.id.toolbar_liste_course);

// Obtenez le menu de la barre d'outils
        Menu menu = toolbar.getMenu();

// Obtenez l'élément de menu spécifique par son ID


// Désactivez l'élément de menu
      //  itempo.setEnabled(false);
      //  SpannableString s = new SpannableString(itempo.getTitle());
        //s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
      //  itempo.setTitle(s);


        search_listeCourse=findViewById(R.id.search_course);

        ViderListeCourse=findViewById(R.id.vider_listeCourse);

        ViderListeCourse.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent

                MyApp myApp = (MyApp) getApplication();
                RequestQueue queue = Volley.newRequestQueue(ListeDeCourse.this);
                int User_id = myApp.getUser_id();
                int User_listeCourse_id = myApp.getUser_listeCourse_id();
                String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products";
                //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                JSONObject jsonBody = new JSONObject();
                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.DELETE,
                        url,
                        jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // La mise à jour a réussi, vous pouvez traiter la réponse si nécessaire
//                                    Toast.makeText(ModifierProduit.this, "Produit mis à jour avec succès", Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Gérez les erreurs de la requête ici
                                //                                  Toast.makeText(ModifierProduit.this, "Erreur lors de la mise à jour du produit", Toast.LENGTH_SHORT).show();

                            }
                        }
                );

                queue.add(request);
               // dataList.clear();

                    adapter.clearData();

                  //  notifyDataSetChanged();



            }
        });




        search_listeCourse.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                String produit = search_listeCourse.getQuery().toString();
                MyApp myApp = (MyApp) getApplication();
                int User_id = myApp.getUser_id();
                int User_listeCourse_id = myApp.getUser_listeCourse_id();

                String url = "http://"+BASE_URL+"/listeCourses/"+   User_listeCourse_id + "/products/" + produit;
              //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;

                //String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;
//                String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;


                ListView listView = findViewById(R.id.myListViewCourse);



                RequestQueue queue = Volley.newRequestQueue(ListeDeCourse.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    for (int i = 0; i < jsonResponse.length(); i++) {
                                        JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                        int id = jsonObject.getInt("id");
                                        String intitule = jsonObject.getString("intitule");
                                        Double quantite = jsonObject.getDouble("quantite");
                                        String mesure = jsonObject.getString("uniteDeMesure");
                                        boolean check = jsonObject.getBoolean("etat");
                                        dataList.add(new Produit(id, intitule, quantite, mesure, check));
                                    }

                                     adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);
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
                         adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);

                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                    }

                });

                queue.add(stringRequest);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                String produit = search_listeCourse.getQuery().toString();

                if (!produit.isEmpty()){

                    MyApp myApp = (MyApp) getApplication();
                    int User_id = myApp.getUser_id();
                    int User_listeCourse_id = myApp.getUser_listeCourse_id();

                    String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products/" + produit;
                  //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;

                    //String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;
//                    String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit;


                    ListView listView = findViewById(R.id.myListViewCourse);

                    ArrayList<Produit> dataList = new ArrayList<>();

                    RequestQueue queue = Volley.newRequestQueue(ListeDeCourse.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    try {
                                        JSONArray jsonResponse = new JSONArray(response);
                                        for (int i = 0; i < jsonResponse.length(); i++) {
                                            JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                            int id = jsonObject.getInt("id");
                                            String intitule = jsonObject.getString("intitule");
                                            Double quantite = jsonObject.getDouble("quantite");
                                            String mesure = jsonObject.getString("uniteDeMesure");
                                            boolean check = jsonObject.getBoolean("etat");
                                            dataList.add(new Produit(id, intitule, quantite, mesure, check));
                                        }

                                        adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);
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
                           adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);

                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);

                        }

                    });

                    queue.add(stringRequest);

                }
                else {
                    MyApp myApp = (MyApp) getApplication();
                    int User_id = myApp.getUser_id();
                    int User_listeCourse_id = myApp.getUser_listeCourse_id();

                    String url = "http://"+BASE_URL+"/listeCourses/"+User_listeCourse_id+"/products";
                   // String url = "http://10.0.2.2:1111/listeCourses/"+User_listeCourse_id+"/products";

                    // String url = "http://192.168.11.100:1111/listeCourses/"+User_listeCourse_id+"/products";
//                    String url = "http://10.0.2.2:1111/listeCourses/"+User_listeCourse_id+"/products";


                    ListView listView = findViewById(R.id.myListViewCourse);

                    ArrayList<Produit> dataList = new ArrayList<>();

                    RequestQueue queue = Volley.newRequestQueue(ListeDeCourse.this);
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
                                            Double quantite = jsonObject.getDouble("quantite");
                                            String mesure = jsonObject.getString("uniteDeMesure");
                                            boolean check=jsonObject.getBoolean("etat");
                                            dataList.add(new Produit(id,intitule,quantite,mesure,check));
                                        }

                                         adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);
                                        adapter.notifyDataSetChanged();



                                        listView.setAdapter(adapter);




                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }



                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //   dataList.add("That didn't work!")
                               adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);

                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);


                        }

                    });

                    queue.add(stringRequest);


                }

                    return false;
                }

        });

        MyApp myApp = (MyApp) getApplication();
        int User_id = myApp.getUser_id();
        int User_listeCourse_id = myApp.getUser_listeCourse_id();

        String url = "http://"+BASE_URL+"/listeCourses/"+User_listeCourse_id+"/products";
      //  String url = "http://10.0.2.2:1111/listeCourses/"+User_listeCourse_id+"/products";

        // String url = "http://192.168.11.100:1111/listeCourses/"+User_listeCourse_id+"/products";
//        String url = "http://10.0.2.2:1111/listeCourses/"+User_listeCourse_id+"/products";


        ListView listView = findViewById(R.id.myListViewCourse);

        ArrayList<Produit> dataList = new ArrayList<>();

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
                                Double quantite = jsonObject.getDouble("quantite");
                                String mesure = jsonObject.getString("uniteDeMesure");
                                boolean check=jsonObject.getBoolean("etat");

                                dataList.add(new Produit(id,intitule,quantite,mesure,check));
                            }

                             adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);
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
                 adapter = new ListeCourseAdapter(ListeDeCourse.this, dataList);

                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);


            }

        });

        queue.add(stringRequest);








        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window_course);
        Menu navBar = bottomNavigationView.getMenu();

        // Sélectionner l'élément "Courses"
        MenuItem menuItem = navBar.findItem(R.id.courses);
        menuItem.setChecked(true);


        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {

            return true;
        });

        navBar.findItem(R.id.budget).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ListeDeCourse.this, budgetActivity.class);
            startActivity(intent);
            finish();
            return true;
        });

        navBar.findItem(R.id.stock).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ListeDeCourse.this, HomeActivity.class);
            startActivity(intent);
           finish();
            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ListeDeCourse.this, RecettesRecommendeActivity.class);
            startActivity(intent);
            finish();
            return true;
        });




    // Gestionnaire de clic pour l'élément "profil1"
        toolbar.setOnMenuItemClickListener(item -> {
        if (item.getItemId() == R.id.profil1) {
            // L'utilisateur a cliqué sur "profil1"
            Intent intent = new Intent(ListeDeCourse.this, ProfilActivity.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.message) {

            Intent profilIntent = new Intent(ListeDeCourse.this, MessageActivity.class);
            startActivity(profilIntent);

            return true;
        }

        else if (item.getItemId() == R.id.poubelle) {
                // L'utilisateur a cliqué sur "profil1"
                Intent intent = new Intent(ListeDeCourse.this, corbeille.class);
                startActivity(intent);
                return true;
            }
        return false;
    });



      // poubelleImage.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View view) {
                // Action à effectuer lors du clic sur l'image
          //      Intent intent = new Intent(ListeDeCourse.this, ModifierProduit.class);
            //    startActivity(intent);
        //    }
      //  });

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