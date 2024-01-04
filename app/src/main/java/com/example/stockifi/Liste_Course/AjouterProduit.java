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
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AjouterProduit extends AppCompatActivity {

    Button ajouterProduitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        ajouterProduitButton = findViewById(R.id.ajoutbasedonnes);





        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ajout);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        Spinner spinnerPoidAjout = findViewById(R.id.spinner_poid_ajoutProduit);

        // Créer un ArrayAdapter en utilisant les éléments du tableau défini dans les ressources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        // Spécifier le layout à utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer l'adaptateur au Spinner
        spinnerPoidAjout.setAdapter(adapter);

        ajouterProduitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNom = findViewById(R.id.editTexte_nomProduit);
                EditText editTextQuantite = findViewById(R.id.quant_ajouterProduit);


                String nomProduit = editTextNom.getText().toString();
                String quantiteProduit = editTextQuantite.getText().toString();
                String poidsProduit = spinnerPoidAjout.getSelectedItem().toString();

                // Créez un objet Produit avec les données récupérées
                //    Produit produit = new Produit(nomProduit, quantiteProduit, poidsProduit);

                // Appelez votre service Spring en utilisant Volley
                MyApp myApp = (MyApp) getApplication();
                RequestQueue queue = Volley.newRequestQueue(AjouterProduit.this);
                int User_id = myApp.getUser_id();
                int User_listeCourse_id = myApp.getUser_listeCourse_id();
                //String url = "http://192.168.11.100:1111/listeCourses/" + User_listeCourse_id + "/products";
                String url = "http://10.0.2.2:1111/listeCourses/" + User_listeCourse_id + "/products";

                // Remplacez {courseId} par la valeur réelle du courseId
                //  url = url.replace("{courseId}", String.valueOf(courseId));

                JSONObject jsonProduit = new JSONObject();
                try {

                        jsonProduit.put("intitule", nomProduit);
                        jsonProduit.put("quantite", quantiteProduit);
                        jsonProduit.put("uniteDeMesure", poidsProduit);

                    // Ajoutez d'autres champs si nécessaire
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (!nomProduit.isEmpty()) {
                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.POST,
                            url,
                            jsonProduit,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Gérez la réponse du serveur en cas de succès
                                    Toast.makeText(AjouterProduit.this, "Produit ajouté avec succès", Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Gérez les erreurs de réseau ou du serveur
                                    //       Toast.makeText(AjouterProduit.this, "Erreur lors de l'ajout du produit", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );

                    // Ajoutez la requête à la file d'attente Volley
                    queue.add(request);

                    Intent intent = new Intent(AjouterProduit.this, ListeDeCourse.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AjouterProduit.this, "vous dever entrer le nom du produit", Toast.LENGTH_SHORT).show();

                }
            }



        });
    }
}