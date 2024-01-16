package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.Liste_Course.AjouterProduit;
import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ajouter_repas extends AppCompatActivity {
    private LinearLayout container;
    private LinearLayout peremtion;
    private TextView peremtiontext;
    private String spinnerText = "Refrigerateur";
    private EditText e ;


    private LinearLayout alert;
    private TextView alerttext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_repas);
        e = findViewById(R.id.editTexte_t);
        e.setText("");
        Intent intent = getIntent();

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ajoutrepas);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        container = findViewById(R.id.container);
        ArrayList<String> quantity = intent.getStringArrayListExtra("stringListExtra");
        ArrayList<Produit> productList =intent.getParcelableArrayListExtra("selectedItems");
        System.out.println(productList);
        System.out.println(quantity);

        Spinner spinner = findViewById(R.id.spinner_options);

        String url = "http://10.0.2.2:1111/categorieDeProduits";
        List<String> itemList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                itemList.add(jsonObject.getString("intitule"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ajouter_repas.this, android.R.layout.simple_spinner_item, itemList);

// Specify the layout to use when the list of choices appears
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
                            spinner.setAdapter(adapter);






                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                itemList.add("That didn't work!");


            }
        });

        queue.add(stringRequest);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = parent.getItemAtPosition(position).toString();
                // Use the selectedText as needed
                spinnerText = selectedText;// Example: Log the selected text
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the situation where nothing is selected
            }
        });

        peremtion=findViewById(R.id.peremtion);
        peremtiontext = findViewById(R.id.peremtiontext);
        peremtiontext.setText("");
        peremtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogPeremstion();
            }
        });

        alert=findViewById(R.id.alert);
        alerttext = findViewById(R.id.alertt);
        alerttext.setText("");
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogalert();
            }
        });

        Button buttonannuler = findViewById(R.id.button_annuler);
        buttonannuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajouter_repas.this, ingredients.class);
                startActivity(intent);
            }
        });

        Button buttonvalider = findViewById(R.id.button_valider);
        buttonvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(peremtiontext.getText());
                System.out.println(alerttext.getText());
                System.out.println(productList);
                System.out.println(spinnerText);
                System.out.println(e.getText());
                if(peremtiontext.getText().equals("") ||
                        alerttext.getText().equals("") ||
                        e.getText().equals("")){

                }
                else {
                    JSONObject jsonProduit = new JSONObject();
                    try {

                        MyApp myApp = (MyApp) getApplication();
                        int User_id = myApp.getUser_id();
                        int User_Stock_id = myApp.getUser_stock_id();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                        jsonProduit.put("intitule", e.getText().toString().trim());
                        jsonProduit.put("datePeremtion", peremtiontext.getText().toString().trim());
                        jsonProduit.put("dateAlert", alerttext.getText().toString().trim());
                        jsonProduit.put("stock",User_Stock_id);
                        JSONArray productsArray = new JSONArray();
                        int l=0;
                        for (Produit product : productList) {
                            JSONObject productObj = new JSONObject();
                            productObj.put("id", product.getId());
                            productObj.put("intitule", product.getIntitule());
                            productObj.put("quantite", quantity.get(l));
                            l++;
                            productObj.put("uniteMesure", product.getUniteMesure());
                            System.out.println(product.getUniteMesure());

                            // Add each product JSON object to the productsArray
                            productsArray.put(productObj);
                        }
                     jsonProduit.put("arraylist_of_product", productsArray);
                      jsonProduit.put("spinnerText", spinnerText.trim());



                        System.out.println(jsonProduit);
                        System.out.println(productList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Instantiate the RequestQueue
                    RequestQueue queue = Volley.newRequestQueue(ajouter_repas.this);
                    String url = "http://10.0.2.2:1111/stocks/repas";

// Create a StringRequest for POST
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.POST,
                            url,
                            jsonProduit,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Gérez la réponse du serveur en cas de succès
                                  //  Toast.makeText(AjouterProduit.this, "Produit ajouté avec succès", Toast.LENGTH_SHORT).show();
                                    System.out.println("yes");
                                    Intent intent = new Intent(ajouter_repas.this, HomeActivity.class);

                                    //  System.out.println(checked);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Gérez les erreurs de réseau ou du serveur
                                    //       Toast.makeText(AjouterProduit.this, "Erreur lors de l'ajout du produit", Toast.LENGTH_SHORT).show();
                                    System.out.println("No");
                                    Intent intent = new Intent(ajouter_repas.this, HomeActivity.class);

                                    //  System.out.println(checked);
                                    startActivity(intent);
                                }
                            }
                    );

// Add the request to the RequestQueue
                    queue.add(request);

                }


            }
        });






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

    private void openDialogPeremstion(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                peremtiontext.setText(selectedDate);


            }
        },year, month, day);

        dialog.show();

    }

    private void openDialogalert(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                alerttext.setText(selectedDate);


            }
        },year, month, day);

        dialog.show();
    }
}