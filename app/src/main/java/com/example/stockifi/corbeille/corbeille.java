package com.example.stockifi.corbeille;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.LoginActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.example.stockifi.Repas.CustomAdapter;
import com.example.stockifi.Repas.ingredients;
import com.example.stockifi.Repas.ingredients_quantity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class corbeille extends AppCompatActivity{
    private corbeilleAdapter adapter;
    private corbeillerepasadapter adapterRepas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corbeille);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_corbeille);

        Menu menu = toolbar.getMenu();
        MenuItem itempo = menu.findItem(R.id.poubelle);

        itempo.setEnabled(false);

        Drawable originalIcon = itempo.getIcon();
        Drawable grayedOutIcon = originalIcon.mutate();
        int disabledColor = getResources().getColor(android.R.color.darker_gray);
        grayedOutIcon.setColorFilter(disabledColor, PorterDuff.Mode.SRC_IN);
        itempo.setIcon(grayedOutIcon);

        TextView textView = findViewById(R.id.aboveListViewText);
        ImageView imageView = findViewById(R.id.trash);

        View.OnClickListener commonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDeleteDialog();
            }
        };

        textView.setOnClickListener(commonClickListener);
        imageView.setOnClickListener(commonClickListener);

        Button produitsButton = findViewById(R.id.produits);
        produitsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyApp myApp = (MyApp) getApplication();
                int User_id = myApp.getUser_id();
                int User_Stock_id = myApp.getUser_stock_id();
                String url = "http://10.0.2.2:1111/corbeille/deletedProduct/ "+ User_Stock_id;
                ListView listView = findViewById(R.id.myListViewCorbeille);

                ArrayList<objet> dataList = new ArrayList<>();

                RequestQueue queue = Volley.newRequestQueue(corbeille.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    for (int i = 0; i < jsonResponse.length(); i++) {
                                        JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                        objet objet = new objet(jsonObject.getInt("id"));
                                        objet.setIntitule(jsonObject.getString("intitule"));
                                        System.out.println(objet);
                                        String intitule = jsonObject.getString("intitule");
                                        dataList.add(objet);
                                    }

                                    adapter = new corbeilleAdapter(corbeille.this, dataList);

                                    listView.setAdapter(adapter);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //dataList.add("That didn't work!");
                        adapter = new corbeilleAdapter(corbeille.this, dataList);

                        listView.setAdapter(adapter);

                    }
                });

                queue.add(stringRequest);

            }

        });

        Button repasButton = findViewById(R.id.repas);
        repasButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyApp myApp = (MyApp) getApplication();
                int User_id = myApp.getUser_id();
                int User_Stock_id = myApp.getUser_stock_id();
                String url = "http://10.0.2.2:1111/corbeille/deletedRecipe/" + User_Stock_id;
                ListView listView = findViewById(R.id.myListViewCorbeille);

                ArrayList<objet> dataList = new ArrayList<>();

                RequestQueue queue = Volley.newRequestQueue(corbeille.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    for (int i = 0; i < jsonResponse.length(); i++) {
                                        JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                        objet objet = new objet(jsonObject.getInt("id"));
                                        objet.setIntitule(jsonObject.getString("intitule"));
                                        System.out.println(objet);
                                        String intitule = jsonObject.getString("intitule");
                                        dataList.add(objet);
                                    }

                                    adapterRepas = new corbeillerepasadapter(corbeille.this, dataList);

                                    listView.setAdapter(adapterRepas);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //dataList.add("That didn't work!");
                        adapterRepas = new corbeillerepasadapter(corbeille.this, dataList);

                        listView.setAdapter(adapterRepas);

                    }
                });

                queue.add(stringRequest);

            }

        });



    }
    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_delete_dialog, null);

        CheckBox checkBoxProducts = dialogView.findViewById(R.id.checkBoxProducts);
        CheckBox checkBoxRecipes = dialogView.findViewById(R.id.checkBoxRecipes);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Handle the actions when OK button is clicked
        buttonOk.setOnClickListener(v -> {
            // Check the state of checkboxes and perform actions accordingly

            if (checkBoxProducts.isChecked() && checkBoxRecipes.isChecked()) {
                MyApp myApp = (MyApp) getApplication();
                int User_Stock_id = myApp.getUser_stock_id();
                String url = "http://10.0.2.2:1111/corbeille/vidercorbeille/" + User_Stock_id;

                RequestQueue queue = Volley.newRequestQueue(corbeille.this);
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(corbeille.this, response, Toast.LENGTH_SHORT).show();
                                adapterRepas.clearData();
                                adapter.clearData();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(corbeille.this, "Error deleting the whole corbeille", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);
            }
            else if (checkBoxRecipes.isChecked()) {
                MyApp myApp = (MyApp) getApplication();
                int User_Stock_id = myApp.getUser_stock_id();
                String url = "http://10.0.2.2:1111/corbeille/viderrepas/" + User_Stock_id;

                RequestQueue queue = Volley.newRequestQueue(corbeille.this);
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(corbeille.this, response, Toast.LENGTH_SHORT).show();
                                adapterRepas.clearData();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(corbeille.this, "Error deleting recipes", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);            }
            else if (checkBoxProducts.isChecked()) {
                MyApp myApp = (MyApp) getApplication();
                int User_Stock_id = myApp.getUser_stock_id();
                String url = "http://10.0.2.2:1111/corbeille/viderproduits/" + User_Stock_id;

                RequestQueue queue = Volley.newRequestQueue(corbeille.this);
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(corbeille.this, response, Toast.LENGTH_SHORT).show();
                                // Notify the adapter to remove all cells after successful deletion
                                adapter.clearData();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(corbeille.this, "Error deleting products", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);
            }

            // Dismiss the dialog
            dialog.dismiss();
        });

        // Handle the actions when Cancel button is clicked
        buttonCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }





}