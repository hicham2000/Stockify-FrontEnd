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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import java.util.ArrayList;

public class ingredients_quantity extends AppCompatActivity {

    private Spinner spinnerDate;
    private ArrayList<String> quantity = new ArrayList<>();
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

        Button displayButton = findViewById(R.id.ajouteringredients);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Function to display content of all EditText fields
                LinearLayout rootView = findViewById(R.id.ingredientscontent); // Replace with your root layout ID
                int childCount = rootView.getChildCount();
                ListView a = (ListView) rootView.getChildAt(0);
                //System.out.println(a);
                int b = a.getChildCount();
                //System.out.println(b);
                quantity.clear();

                for (int i = 0; i < b; i++) {
                    LinearLayout vieww = (LinearLayout) a.getChildAt(i);
                    View view = vieww.getChildAt(1);



                    EditText editText = (EditText) view;
                    String content = editText.getText().toString();
                    quantity.add(content);


                }

                if(quantity.size() == productList.size() ){
                    for (int i =0 ; i<quantity.size() ; i++){
                        Double asfd = Double.parseDouble(quantity.get(i));

                        if (asfd >  productList.get(i).getQuantite() || asfd == 0){

                            break;
                        }
                        if (i == quantity.size() -1){
                            Intent intent = new Intent(ingredients_quantity.this, ajouter_repas.class);
                            intent.putParcelableArrayListExtra("selectedItems", productList);
                            intent.putStringArrayListExtra("stringListExtra", quantity);
                            startActivity(intent);
                        }

                    }
                }




            }
        });
    }

    private void displayEditTextContent() {


    }




}