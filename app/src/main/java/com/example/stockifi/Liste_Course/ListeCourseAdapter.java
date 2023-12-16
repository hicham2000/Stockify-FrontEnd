package com.example.stockifi.Liste_Course;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.stockifi.R;

import java.util.ArrayList;

public class ListeCourseAdapter extends ArrayAdapter<String> {


        private ArrayList<Boolean> checkedPositions = new ArrayList<>();

        public ListeCourseAdapter(Context context, ArrayList<String> data) {
            super(context, 0, data);
            for (int i = 0; i < data.size(); i++) {
                checkedPositions.add(false);
            }
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listecourselist, parent, false);
            }

            CheckBox checkBox = convertView.findViewById(R.id.checkBox1);
            checkBox.setChecked(checkedPositions.get(position));
            checkBox.setText(getItem(position));
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


