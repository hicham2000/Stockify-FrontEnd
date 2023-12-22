package com.example.stockifi.Repas;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Produit> implements Serializable {
    private ArrayList<Boolean> checkedPositions = new ArrayList<>();
    private ArrayList<Produit> checkedPosition = new ArrayList<>();

    private ArrayList<Produit> Data= new ArrayList<>();;


    public CustomAdapter(Context context, ArrayList<Produit> data) {
        super(context, 0, data);
        Data = data;
        for (int i = 0; i < data.size(); i++) {
            checkedPositions.add(false);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredientlist, parent, false);
        }

        RadioButton radioButton = convertView.findViewById(R.id.radioButtoningredient);
        radioButton.setChecked(checkedPositions.get(position));
        radioButton.setText(getItem(position).getIntitule());
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPositions.set(position, !checkedPositions.get(position));
                for(int i =0 ; i<Data.size() ; i++){
                    if(Data.get(i).getIntitule().equals(radioButton.getText().toString())){
                        if(!checkedPosition.contains(Data.get(i))){
                            checkedPosition.add(Data.get(i));
                        }
                        else{
                            checkedPosition.remove(Data.get(i));
                        }
                    }
                }


             // System.out.println(checkedPosition);
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    public ArrayList<Produit> getCheckedPositions() {
        return checkedPosition;
    }
}
