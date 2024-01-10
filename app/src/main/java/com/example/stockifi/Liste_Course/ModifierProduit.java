package com.example.stockifi.Liste_Course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ModifierProduit extends AppCompatActivity {

    private static final String BASE_URL = "192.168.11.100:1111";
    EditText nom;
    EditText quantite;


    Button sauvegarderButton;



    private int getPositionInArray(String value, String[] array) {
        return Arrays.asList(array).indexOf(value);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_produit);

     //   Spinner spinner = findViewById(R.id.spinner_poid_ajoutModif1);


        Spinner spinnerPoidAjoutmodif = findViewById(R.id.spinner_poid_ajoutModif1);

        nom=findViewById(R.id.editTexte_modifProduit);
        quantite=findViewById(R.id.quant_ajoutModif);


        Bundle extras=getIntent().getExtras();
        int id= new Integer(extras.getInt("id"));
        String titre=new String(extras.getString("intitule"));
        double quantitee = extras.getDouble("quantite");
        String mesurePoid=new String(extras.getString("mesure"));





     //   String[] poidsArray = getResources().getStringArray(R.array.poids);
     //   int position = getPositionInArray(mesurePoid, poidsArray);

     //   spinnerPoidAjoutmodif.setSelection(position);
       // image200.setImageResource(image);
        nom.setText(titre);
        String quantiteString = String.valueOf(quantitee);
        quantite.setText(quantiteString);
      // spinnerPoidAjoutmodif.getI;
        sauvegarderButton = findViewById(R.id.sauvegarder_prod_modif);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        // Spécifier le layout à utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer l'adaptateur au Spinner
       spinnerPoidAjoutmodif.setAdapter(adapter);

        for (int i = 0; i < spinnerPoidAjoutmodif.getCount(); i++) {
            String itemValue = (String) spinnerPoidAjoutmodif.getItemAtPosition(i);

            // Vérifier si la valeur correspond à celle recherchée
            if (itemValue != null && itemValue.equals(mesurePoid)) {
                // Définir la sélection sur la position correspondante
                spinnerPoidAjoutmodif.setSelection(i);
                break; // Sortir de la boucle une fois que la position est trouvée
            }
        }
        sauvegarderButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     EditText editTextNom = findViewById(R.id.editTexte_modifProduit);
                                                     EditText editTextQuantite = findViewById(R.id.quant_ajoutModif);


                                                     String nomProduit = editTextNom.getText().toString();
                                                     String quantiteProduit = editTextQuantite.getText().toString();
                                                     String poidsProduit = spinnerPoidAjoutmodif.getSelectedItem().toString();


                                                     MyApp myApp = (MyApp) getApplication();
                                                     RequestQueue queue = Volley.newRequestQueue(ModifierProduit.this);
                                                     int User_id = myApp.getUser_id();
                                                     int User_listeCourse_id = myApp.getUser_listeCourse_id();

                                                     String url = "http://"+BASE_URL+"/listeCourses/" + User_listeCourse_id + "/products/" + id;
                                                  //   String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + id;

                                                     //String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products/" + id;
                                                  //   String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products/" + id;

                                                     JSONObject jsonBody = new JSONObject();

                                                     try {
                                                         jsonBody.put("intitule", nomProduit);
                                                         jsonBody.put("quantite", quantiteProduit);
                                                         jsonBody.put("uniteDeMesure", poidsProduit);
                                                         // Ajoutez d'autres champs si nécessaire
                                                     } catch (JSONException e) {
                                                         e.printStackTrace();
                                                     }

                                                     JsonObjectRequest request = new JsonObjectRequest(
                                                             Request.Method.PUT,
                                                             url,
                                                             jsonBody,
                                                             new Response.Listener<JSONObject>() {
                                                                 @Override
                                                                 public void onResponse(JSONObject response) {
                                                                     // La mise à jour a réussi, vous pouvez traiter la réponse si nécessaire
                                                                  //   Toast.makeText(ModifierProduit.this, "Produit mis à jour avec succès", Toast.LENGTH_SHORT).show();
                                                                 }
                                                             },
                                                             new Response.ErrorListener() {
                                                                 @Override
                                                                 public void onErrorResponse(VolleyError error) {
                                                                     // Gérez les erreurs de la requête ici
                                                                //     Toast.makeText(ModifierProduit.this, "Erreur lors de la mise à jour du produit", Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }
                                                     );

                                                     queue.add(request);
                                                     Intent intent = new Intent(ModifierProduit.this, ListeDeCourse.class);
                                                     startActivity(intent);
                                                 }

                                             });


      //  mesure.setText(date);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_modif);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });



        // Créer un ArrayAdapter en utilisant les éléments du tableau défini dans les ressources


     //   Bundle extras=getIntent().getExtras();

       // String titre=new String(extras.getString("title"));
    }
}