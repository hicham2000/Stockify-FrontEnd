package com.example.stockifi.corbeille;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class corbeilleAdapter extends ArrayAdapter<objet> {
    private ArrayList<objet> data;
    private ArrayList<Boolean> checkedPositions;

    private BackendManager backendManager = new BackendManager(getContext());

    private MyApp myApp = (MyApp) (MyApp) getContext().getApplicationContext();

    public corbeilleAdapter(Context context, ArrayList<objet> data) {
        super(context, 0, data);
        this.data = data;
        checkedPositions = new ArrayList<>(Collections.nCopies(data.size(), false));
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_view_corbeille_produit, parent, false);
        }


        TextView textViewP = convertView.findViewById(R.id.textViewP);
        Button buttonRecup=convertView.findViewById(R.id.recuperer);
        Button buttonSupp=convertView.findViewById(R.id.supprimer);
        LinearLayout linearoutProduit=convertView.findViewById(R.id.linearoutProduit);

        textViewP.setText(data.get(position).getIntitule());


        buttonRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int productId = data.get(position).getId();
                int stockId = myApp.getUser_stock_id();

                backendManager.recupererUnProduitFromCorbeille((long) stockId, (long) productId, new BackendManager.BackendResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        data.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(getContext().getApplicationContext(), "Erreur lors de la mise à jour du isDelete: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        buttonSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int productId = data.get(position).getId();
                int stockId = myApp.getUser_stock_id();

                backendManager.supprimerDefUnProduitFromCorbeille((long) stockId, (long) productId, new BackendManager.BackendResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        if (position >= 0 && position < data.size()) {
                            data.remove(position);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(getContext().getApplicationContext(), "Erreur lors de la mise à jour du isDelete: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        return convertView;
    }
}
