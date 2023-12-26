package com.example.stockifi.corbeille;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import com.example.stockifi.R;

import java.util.ArrayList;
import java.util.Collections;

public class corbeillerepasadapter extends ArrayAdapter<objet> {
    private ArrayList<objet> data;
    private ArrayList<Boolean> checkedPositions;

    public corbeillerepasadapter(Context context, ArrayList<objet> data) {
        super(context, 0, data);
        this.data = data;
        checkedPositions = new ArrayList<>(Collections.nCopies(data.size(), false));
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_view_corbeille_repas, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBoxrepas);
        Button buttonRecup=convertView.findViewById(R.id.buttonrecuprepas);
        Button buttonSupp=convertView.findViewById(R.id.buttonsuppprepas);

        checkBox.setChecked(checkedPositions.get(position));
        checkBox.setText(data.get(position).getIntitule());


        return convertView;
    }
}
