package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.stockifi.R;

import java.util.ArrayList;

public class ingredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        ListView listView = findViewById(R.id.myListViewingredient);

        ArrayList<String> dataList = new ArrayList<>();


        dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 3");
        dataList.add("Item 3");
        CustomAdapter adapter = new CustomAdapter(this, dataList);

        listView.setAdapter(adapter);

    }
}