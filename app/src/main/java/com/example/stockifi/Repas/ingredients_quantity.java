package com.example.stockifi.Repas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import java.util.ArrayList;

public class ingredients_quantity extends AppCompatActivity {

    private Spinner spinnerDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_quantity);
        Intent intent = getIntent();

            ArrayList<Produit> productList =intent.getParcelableArrayListExtra("selectedItems");




        ListView listView = findViewById(R.id.myListViewingredientquantity);
        ArrayAdapter<Produit> adapter = new ArrayAdapter<Produit>(this, R.layout.ingredient_quantity_list, R.id.textViewingredients_quantity, productList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textViewProductName = view.findViewById(R.id.textViewingredients_quantity);
                TextView unit = view.findViewById(R.id.textViewingredients_quantity_unit);
                unit.setText(productList.get(position).getUniteMesure());
             //   System.out.println(productList.get(position));



                textViewProductName.setText(productList.get(position).getIntitule());


                return view;
            }
        };

        listView.setAdapter(adapter);


        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }




}