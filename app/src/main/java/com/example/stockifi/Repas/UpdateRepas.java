package com.example.stockifi.Repas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Home.HomeActivity;
import com.example.stockifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateRepas extends AppCompatActivity {

    private LinearLayout container;
    private LinearLayout peremtion;
    private LinearLayout alertr;
    int repasId;
    int p1 =0;
    int a1 =0;

    EditText name;
    TextView per;
    TextView alert;
    String categories;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_repas);

        repasId = getIntent().getIntExtra("repasId",0);
        categories = getIntent().getStringExtra("cat");
        System.out.println(repasId);



        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_updaterepas);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });



        container = findViewById(R.id.containerupdtae);
        String url1 = "http://10.0.2.2:1111/api/Ingredients/"+repasId;
        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        System.out.println("hicham");

                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                String name = jsonObject.getString("intitule");
                                String unit = jsonObject.getString("uniteDeMesure");
                                Double qu = jsonObject.getDouble("quantity");

                                TextView textView = new TextView(UpdateRepas.this);
                                textView.setText(qu+ " " + unit + " " + name);
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
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hhhhhhhhhcxvxcvxcvx");

            }
        });
        queue1.add(stringRequest1);


        name = findViewById(R.id.editTexte_tupdtae);
        per = findViewById(R.id.peremtiontextupdtae);
        alert = findViewById(R.id.alerttUpdate);

        String url = "http://10.0.2.2:1111/stocks/repasbyid/"+repasId;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
                            System.out.println("hhh"+ingredientsArray);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            String intituleValue = jsonObject.getString("intitule");
                            String categories = jsonObject.getString("categories");
                            String al = jsonObject.getString("dateAlert");
                            String pero = jsonObject.getString("datePeremtion");
                            name.setText(intituleValue);
                            //cat.setText(categories);
                           alert.setText(al.split("T")[0]);
                           per.setText(pero.split("T")[0]);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hhhhhhhhhcxvxcvxcvx");

            }
        });

        queue.add(stringRequest);



        Spinner spinner = findViewById(R.id.spinner_options);

        url = "http://10.0.2.2:1111/categorieDeProduits";
        List<String> itemList = new ArrayList<>();
        queue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject = jsonResponse.getJSONObject(i);
                                itemList.add(jsonObject.getString("intitule"));
                                if(categories.equals(jsonObject.getString("intitule"))  ){
                                    index = i;
                                }
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateRepas.this, android.R.layout.simple_spinner_item, itemList);

// Specify the layout to use when the list of choices appears
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
                            spinner.setAdapter(adapter);


// Définissez la sélection du Spinner sur l'index de l'élément par défaut
                            spinner.setSelection(index);






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
                categories = selectedText;// Example: Log the selected text
                System.out.println(categories);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the situation where nothing is selected
            }
        });

        peremtion=findViewById(R.id.peremtion);
        peremtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogPeremstion();
            }
        });

        alertr=findViewById(R.id.alert);

        alertr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialogalert();
            }
        });

        Button update = findViewById(R.id.button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog
                if (!name.getText().toString().equals("")){
                    System.out.println(name.getText());
                    System.out.println(categories);
                    System.out.println(per.getText());
                    System.out.println(alert.getText());

                    try {
                        JSONObject jsonProduit = new JSONObject();
                        MyApp myApp = (MyApp) getApplication();
                        int User_id = myApp.getUser_id();
                        int User_Stock_id = myApp.getUser_stock_id();
                        jsonProduit.put("intitule", name.getText().toString().trim());
                        if(p1 == 0){
                            jsonProduit.put("datePeremtion", per.getText().toString().trim().split("-")[0].trim()+"."+per.getText().toString().trim().split("-")[1].trim()+"."+per.getText().toString().trim().split("-")[2].trim());

                        }
                        else{
                            jsonProduit.put("datePeremtion", per.getText());

                        }
                        if(a1 == 0){
                            jsonProduit.put("dateAlert", alert.getText().toString().trim().split("-")[0].trim()+"."+alert.getText().toString().trim().split("-")[1].trim()+"."+alert.getText().toString().trim().split("-")[2].trim());

                        }
                        else {
                            jsonProduit.put("dateAlert", alert.getText());

                        }
                        jsonProduit.put("categorie",categories);
                        jsonProduit.put("id",repasId);

                        System.out.println(jsonProduit);

                        RequestQueue queue = Volley.newRequestQueue(UpdateRepas.this);
                        String url = "http://10.0.2.2:1111/stocks/repas";

// Create a StringRequest for POST
                        JsonObjectRequest request = new JsonObjectRequest(
                                Request.Method.PUT,
                                url,
                                jsonProduit,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Gérez la réponse du serveur en cas de succès
                                        //  Toast.makeText(AjouterProduit.this, "Produit ajouté avec succès", Toast.LENGTH_SHORT).show();
                                        System.out.println("yes");
                                        Intent intent = new Intent(UpdateRepas.this, HomeActivity.class);

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
                                        Intent intent = new Intent(UpdateRepas.this, HomeActivity.class);

                                        //  System.out.println(checked);
                                        startActivity(intent);
                                    }
                                }
                        );

// Add the request to the RequestQueue
                        queue.add(request);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


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

                String selectedDate =String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                p1 =1;
                per.setText(selectedDate);


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

                String selectedDate =String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                alert.setText(selectedDate);
                a1 =1;


            }
        },year, month, day);

        dialog.show();
    }
}