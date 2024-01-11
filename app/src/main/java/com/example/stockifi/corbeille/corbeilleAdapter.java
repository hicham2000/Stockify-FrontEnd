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

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

        CheckBox checkBox = convertView.findViewById(R.id.checkBoxP);
        Button buttonRecup=convertView.findViewById(R.id.recuperer);
        Button buttonSupp=convertView.findViewById(R.id.supprimer);
        LinearLayout linearoutProduit=convertView.findViewById(R.id.linearoutProduit);

        checkBox.setChecked(checkedPositions.get(position));
        checkBox.setText(data.get(position).getIntitule());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPositions.set(position, !checkedPositions.get(position));
                objet obj = getItem(position);
                if (obj != null) {
                    if (checkedPositions.get(position)) {
                        linearoutProduit.setVisibility(View.VISIBLE);
                    } else {
                        linearoutProduit.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        return convertView;
    }
}
