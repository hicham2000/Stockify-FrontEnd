package com.example.stockifi.Liste_Course;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ListeCourseAdapter extends ArrayAdapter<Produit> {

    private ArrayList<Produit> data;
    private ArrayList<Boolean> checkedPositions;

    public ListeCourseAdapter(Context context, ArrayList<Produit> data) {
        super(context, 0, data);
        this.data = data;
        checkedPositions = new ArrayList<>(Collections.nCopies(data.size(), false));
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
                    String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products/" + produit.getId();
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
                    Intent intent = new Intent(getContext(), ListeDeCourse.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getContext().startActivity(intent);


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

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPositions.set(position, !checkedPositions.get(position));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}



