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

import com.example.stockifi.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private ArrayList<Boolean> checkedPositions = new ArrayList<>();
    private ArrayList<String> checkedPosition = new ArrayList<>();


    public CustomAdapter(Context context, ArrayList<String> data) {
        super(context, 0, data);
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
        radioButton.setText(getItem(position));
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPositions.set(position, !checkedPositions.get(position));
                if(!checkedPosition.contains(radioButton.getText().toString())){
                    checkedPosition.add(radioButton.getText().toString());
                }
                else{
                    checkedPosition.remove(radioButton.getText().toString());
                }

                System.out.println(checkedPosition);
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    public ArrayList<String> getCheckedPositions() {
        System.out.println(checkedPosition);
        return checkedPosition;
    }
}
