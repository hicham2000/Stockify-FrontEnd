package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import java.util.ArrayList;

public class ajouter_repas extends AppCompatActivity {
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_repas);

        Intent intent = getIntent();

        container = findViewById(R.id.container);
        ArrayList<String> quantity = intent.getStringArrayListExtra("stringListExtra");
        ArrayList<Produit> productList =intent.getParcelableArrayListExtra("selectedItems");
        System.out.println(productList);
        System.out.println(quantity);


        for(int i =0; i<productList.size();i++){
            // Create a new TextView
            TextView textView = new TextView(this);
            textView.setText(quantity.get(i)+ " " + productList.get(i).getUniteMesure() + " " + productList.get(i).getIntitule());
            textView.setTextColor(Color.WHITE); // Set text color to blue
            textView.setTextSize(20);

            // Set layout parameters for the TextView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT

            );
            textView.setLayoutParams(layoutParams);

            // Add the TextView to the LinearLayout
            container.addView(textView);
        }

        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}