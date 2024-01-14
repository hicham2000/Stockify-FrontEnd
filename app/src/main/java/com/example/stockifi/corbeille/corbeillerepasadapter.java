package com.example.stockifi.corbeille;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.stockifi.BackendManager;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;
import com.example.stockifi.UpdateRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class corbeillerepasadapter extends ArrayAdapter<objet> {
    private ArrayList<objet> data;
    private ArrayList<Boolean> checkedPositions;

    private BackendManager backendManager = new BackendManager(getContext());


    private MyApp myApp = (MyApp) (MyApp) getContext().getApplicationContext();

    public corbeillerepasadapter(Context context, ArrayList<objet> data) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_view_corbeille_repas, parent, false);
        }

        TextView textViewR = convertView.findViewById(R.id.textViewR);
        Button buttonRecup=convertView.findViewById(R.id.buttonrecuprepas);
        Button buttonSupp=convertView.findViewById(R.id.buttonsuppprepas);

        textViewR.setText(data.get(position).getIntitule());
        ImageView ellipseG = convertView.findViewById(R.id.ellipseG);

        if (data.get(position).getGaspille() == 1) {
            ellipseG.setVisibility(View.VISIBLE);
        } else {
            ellipseG.setVisibility(View.INVISIBLE);
        }

        /*buttonRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int repasId = data.get(position).getId();
                //int stockId = myApp.getUser_stock_id();

                backendManager.recupererUnProduitFromCorbeille((long) stockId, (long) repasId, new BackendManager.BackendResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        // Traitez le succès ici si nécessaire
                    }

                    @Override
                    public void onError(Exception error) {
                        // Traitez l'erreur ici si nécessaire
                        Toast.makeText(getContext().getApplicationContext(), "Erreur lors de la mise à jour du isDelete: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/
        buttonRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int repasId = data.get(position).getId();
                int stockId = myApp.getUser_stock_id();
                backendManager.recupererDeletedRepasFromCorbeille((long) stockId, (long) repasId, new BackendManager.BackendResponseCallback() {
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

        /*buttonSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int repasId = data.get(position).getId();
                int stockId = myApp.getUser_stock_id();

                backendManager.supprimerDefUnRepasFromCorbeille((long) stockId, (long) repasId, new BackendManager.BackendResponseCallback() {
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
        });*/
        buttonSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int repasId = data.get(position).getId();
                int stockId = myApp.getUser_stock_id();

                backendManager.supprimerDefPermanentlyUnRepasFromCorbeille((long) stockId, (long) repasId, new BackendManager.BackendResponseCallback() {
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
