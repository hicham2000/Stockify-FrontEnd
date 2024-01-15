package com.example.stockifi.Liste_Course;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ListeCourseAdapter extends ArrayAdapter<Produit> {

    private ArrayList<Produit> data;
    private ArrayList<Boolean> checkedPositions;
    private static final String BASE_URL = "192.168.11.100:1111";
 //   private static final String BASE_URL = "10.0.2.2:1111";

    public ListeCourseAdapter(Context context, ArrayList<Produit> data) {
        super(context, 0, data);
        this.data = data;
        checkedPositions = new ArrayList<>(Collections.nCopies(data.size(), false));
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listecourselist, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox1);
        ImageView modif=convertView.findViewById(R.id.modifier_prod);
        ImageView suppr=convertView.findViewById(R.id.supprim);
        ImageView visualise=convertView.findViewById(R.id.visualis_prod);
        if (data.get(position).getCheck()){
                  checkedPositions.set(position,true);
        }
        checkBox.setChecked(checkedPositions.get(position));
        checkBox.setText(data.get(position).getIntitule());







        suppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit produit=getItem(position);
                if (produit!=null){
                    MyApp myApp = (MyApp) (MyApp) getContext().getApplicationContext();
                    RequestQueue queue = Volley.newRequestQueue(ListeCourseAdapter.this.getContext());
                    int User_id = myApp.getUser_id();
                    int User_listeCourse_id = myApp.getUser_listeCourse_id();

                    String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                  //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();

                    //String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
              //      String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();

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
                    if (position >= 0 && position < data.size()) {
                        data.remove(position);
                        notifyDataSetChanged();
                    }


                }
            }
        });
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produit produit = getItem(position);
                if (produit != null) {
                    // Créez une intention pour l'activité ModifierProduit
                    Intent intent = new Intent(getContext(), ModifierProduit.class);

                    // Ajoutez les données du produit à l'intention
                    intent.putExtra("id", produit.getId());
                    intent.putExtra("intitule", produit.getIntitule());
                    intent.putExtra("quantite", produit.getQuantite());
                    intent.putExtra("mesure", produit.getUniteMesure());

                    // Démarrez l'activité ModifierProduit
                    getContext().startActivity(intent);
                }
            }
        });

        visualise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produit produit = getItem(position);
                if (produit != null) {
                    // Créez une intention pour l'activité ModifierProduit
                    Intent intent = new Intent(getContext(), information_produit_listeCourse.class);

                    // Ajoutez les données du produit à l'intention
                    intent.putExtra("id", produit.getId());
                    intent.putExtra("intitule", produit.getIntitule());
                    intent.putExtra("quantite", produit.getQuantite());
                    intent.putExtra("mesure", produit.getUniteMesure());

                    // Démarrez l'activité ModifierProduit
                    getContext().startActivity(intent);
                }
            }
        });


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   boolean isChecked = checkedPositions.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                if (data.get(position).getCheck()==false) {
                builder.setTitle("Confirmation d'ajout du produit au stock");

                    // If the checkbox is not checked and the user clicks to check it
                    builder.setMessage("Vous voulez ajouter le produit acheter a votre stock?");
                    builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked "Oui," perform the action




                            // Your existing code to toggle the checkbox and update the server
                            checkedPositions.set(position, !checkedPositions.get(position));
                            Produit produit = getItem(position);
                            if (produit != null) {
                                MyApp myApp = (MyApp) (MyApp) getContext().getApplicationContext();
                                RequestQueue queue = Volley.newRequestQueue(ListeCourseAdapter.this.getContext());
                                int User_id = myApp.getUser_id();
                                int User_listeCourse_id = myApp.getUser_listeCourse_id();
                                int User_stock_id = myApp.getUser_stock_id();
                                produit.setCheck(!produit.getCheck());
                                String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                                String url_stock = "http://"+BASE_URL+"/stocks/" + User_stock_id + "/products";

                                //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                                JSONObject jsonBody = new JSONObject();
                                JSONObject jsonBodyStock = new JSONObject();
                                try {
                                    jsonBody.put("quantite", produit.getQuantite());
                                    jsonBody.put("intitule", produit.getIntitule());
                                    jsonBody.put("uniteDeMesure", produit.getUniteMesure());
                                    jsonBody.put("etat", true);

                                    // Ajoutez d'autres champs si nécessaire
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    jsonBodyStock.put("id_produitCourse",produit.getId());
                                    jsonBodyStock.put("quantite", produit.getQuantite());
                                    jsonBodyStock.put("intitule", produit.getIntitule());
                                    jsonBodyStock.put("uniteDeMesure", produit.getUniteMesure());


                                    // Ajoutez d'autres champs si nécessaire
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                JsonObjectRequest request = new JsonObjectRequest(
                                        Request.Method.PUT,
                                        url,
                                        jsonBody,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // La mise à jour a réussi, vous pouvez traiter la réponse si nécessaire
                                                //   Toast.makeText(ModifierProduit.this, "Produit mis à jour avec succès", Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Gérez les erreurs de la requête ici
                                                //     Toast.makeText(ModifierProduit.this, "Erreur lors de la mise à jour du produit", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                );
                                JsonObjectRequest requestStock = new JsonObjectRequest(
                                        Request.Method.POST,
                                        url_stock,
                                        jsonBodyStock,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // La mise à jour a réussi, vous pouvez traiter la réponse si nécessaire
                                                //   Toast.makeText(ModifierProduit.this, "Produit mis à jour avec succès", Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Gérez les erreurs de la requête ici
                                                //     Toast.makeText(ModifierProduit.this, "Erreur lors de la mise à jour du produit", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                );
                                queue.add(requestStock);
                                queue.add(request);
                                notifyDataSetChanged();
                            }
                        }
                    });
                    builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked "Non," do nothing or handle as needed
                            checkedPositions.set(position,false);
                            checkBox.setChecked(checkedPositions.get(position));
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // If the checkbox is checked and the user clicks to uncheck it
                    builder.setTitle("Confirmation d'annulaion du produit au stock");
                    builder.setMessage("Vous voulez enlever le produit de votre stock?");
                    builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked "Oui," perform the action

                            // Your existing code to toggle the checkbox and update the server
                            checkedPositions.set(position, !checkedPositions.get(position));
                            Produit produit = getItem(position);
                            if (produit != null) {
                                MyApp myApp = (MyApp) (MyApp) getContext().getApplicationContext();
                                RequestQueue queue = Volley.newRequestQueue(ListeCourseAdapter.this.getContext());
                                int User_id = myApp.getUser_id();
                                int User_stock_id = myApp.getUser_stock_id();
                                int User_listeCourse_id = myApp.getUser_listeCourse_id();
                                produit.setCheck(!produit.getCheck());
                                String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                                String urlStock = "http://"+BASE_URL+"/stocks/" + User_stock_id + "/products/"+produit.getId()+"/delete-course";
                                //  String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
                                JSONObject jsonBody = new JSONObject();

                                try {
                                    jsonBody.put("quantite", produit.getQuantite());
                                    jsonBody.put("intitule", produit.getIntitule());
                                    jsonBody.put("uniteDeMesure", produit.getUniteMesure());
                                    jsonBody.put("etat", false);

                                    // Ajoutez d'autres champs si nécessaire
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                JSONObject jsonBodyStock = new JSONObject();
                                JsonObjectRequest requestStock = new JsonObjectRequest(
                                        Request.Method.DELETE,
                                        urlStock,
                                        jsonBodyStock,
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



                                JsonObjectRequest request = new JsonObjectRequest(
                                        Request.Method.PUT,
                                        url,
                                        jsonBody,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // La mise à jour a réussi, vous pouvez traiter la réponse si nécessaire
                                                //   Toast.makeText(ModifierProduit.this, "Produit mis à jour avec succès", Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Gérez les erreurs de la requête ici
                                                //     Toast.makeText(ModifierProduit.this, "Erreur lors de la mise à jour du produit", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                );
                                queue.add(requestStock);
                                queue.add(request);
                                notifyDataSetChanged();
                            }
                        }
                    });
                    builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked "Non," do nothing or handle as needed
                            checkedPositions.set(position,true);
                            checkBox.setChecked(checkedPositions.get(position));
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }


            }
        });

        return convertView;
    }
}



