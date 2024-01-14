package com.example.stockifi;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.Liste_Course.ListeDeCourse;

import com.example.stockifi.recettes.RecetteModel;
import com.example.stockifi.recettes.RecettesAdapter;
import com.example.stockifi.recettes.RecettesRecommendeActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfilActivity extends AppCompatActivity {

    private static final int MENU_POUBELLE = R.id.poubelle;
    private static final int MENU_MESSAGE = R.id.message;
    private static final int MENU_PROFIL = R.id.profil1;

    private static final String SWITCH3_STATE_KEY = "switch3StateKey";




    private LinearLayout pickDateButton;
    private TextView date_naissace;

    private TextView produitCuisine;
    private TextView produitGaspille;
    private int year, month, day;
    private boolean titreSelectionne = false;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String TAILLE_KEY = "tailleKey";

    //private static final String PREFS_NAME = "MyPrefsFile";
    private static final String SPINNER_SELECTION_KEY = "spinnerSelectionKey";

    private static final String SPINNER_GENDER_SELECTION_KEY = "spinnerGenderSelectionKey";

    private static final String SELECTED_DATE_KEY = "selectedDateKey";

    private static final String WEIGHT_KEY = "weightKey";
    private static final String SPINNER_POIDS_SELECTION_KEY = "spinnerPoidsSelectionKey";

    private static final String SPINNER_REGIME_SELECTION_KEY = "spinnerRegimeSelectionKey";

    private static final String SELECTED_RADIO_BUTTON_KEY = "selectedRadioButtonKey";

    private static final String SPINNER_DEVISE_SELECTION_KEY = "spinnerDeviseSelectionKey";

    private static final String SWITCH_STATE_KEY = "switchStateKey";
    private static final String SWITCH2_STATE_KEY = "switch2StateKey";
    private static final String SWITCH4_STATE_KEY = "switch4StateKey";
    private static final String QUANTITE_CRI_KEY = "quantiteCriKey";

    private static final String SPINNER_MESURE_SELECTION_KEY = "spinnerMesureSelectionKey";

    private static final String SPINNER_DATE_PER_SELECTION_KEY = "spinnerDatePerSelectionKey";

   private static final String BASE_URL = "10.0.2.2:1111";
 // private static final String BASE_URL = "192.168.11.100:1111";
    private TextView nomProfilView;
    private TextView emailProfilView;

    private EditText editTextQuantiteCri;

    private EditText editTextPoids;
    private EditText editTextDelaiRappel;


    private EditText editTextTaille;


    Spinner spinnerGender;
    Spinner spinnerTaille;
    Spinner spinnerPoids;
    Spinner spinnerRegime;
    Spinner spinnerDevise;
    Spinner spinnerDate;
    Spinner spinnerQuantite;
    Spinner spinnerPerem;

    private RadioButton radioButtonMetric;
    private RadioButton radioButtonImperial;


    private static final String DELAI_RAPPEL_KEY = "delaiRappelKey";
    private static final String SPINNER_DATE_SELECTION_KEY = "spinnerDateSelectionKey";
    private static final String PEREMPT_KEY = "peremptKey";

    private EditText editTextPerempt;

    private Button buttonSupCompte ;
    private Button LogoutButton;

    private BackendManager backendManager;

    private  int currentUserId;
    private int stockUserId;
    private int listeCourseId;



    public void onBouttonCondiClick(View view) {
        // Code à exécuter lorsque le LinearLayout est cliqué
        Intent intent = new Intent(this, ConditionUtilisationActivity.class);
        startActivity(intent);
    }


    private void openInstagramProfile() {
        // Check if Instagram is installed
        if (isInstagramInstalled()) {
            // If installed, open the Instagram profile
            Uri uri = Uri.parse("http://instagram.com/_u/instagram"); // Change 'instagram' to the actual username
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.instagram.android");
            startActivity(intent);
        } else {
            // If Instagram is not installed, you can redirect to the Instagram website or handle it as needed
            // For example:
            Uri uri = Uri.parse("http://instagram.com/"); // Redirect to Instagram website
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private boolean isInstagramInstalled() {
        try {
            // Try to get the application info of Instagram
            getPackageManager().getApplicationInfo("com.instagram.android", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // If Instagram is not found, catch the exception
            return false;
        }
    }

    private void openTwitterProfile() {
        // Check if Twitter is installed
        if (isTwitterInstalled()) {
            // If installed, open the Twitter profile
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("twitter://user?screen_name=twitter")); // Change 'twitter' to the actual username
            startActivity(intent);
        } else {
            // If Twitter is not installed, you can redirect to the Twitter website or handle it as needed
            // For example:
            Uri uri = Uri.parse("https://twitter.com/twitter"); // Redirect to Twitter website
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private boolean isTwitterInstalled() {
        try {
            // Try to get the application info of Twitter
            getPackageManager().getApplicationInfo("com.twitter.android", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // If Twitter is not found, catch the exception
            return false;
        }
    }

    private void openFacebookProfile() {
        // Check if Facebook is installed
        if (isFacebookInstalled()) {
            // If installed, open the Facebook profile
            Uri uri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/facebook"); // Change 'facebook' to the actual username or profile URL
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            // If Facebook is not installed, you can redirect to the Facebook website or handle it as needed
            // For example:
            Uri uri = Uri.parse("https://www.facebook.com/facebook"); // Redirect to Facebook website
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private boolean isFacebookInstalled() {
        try {
            // Try to get the application info of Facebook
            getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // If Facebook is not found, catch the exception
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_profil);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        MyApp myApp = (MyApp) getApplication();

        currentUserId = myApp.getUser_id();
        listeCourseId = myApp.getUser_listeCourse_id();
        stockUserId = myApp.getUser_stock_id();

        if(currentUserId < 0) {
            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
            startActivity(intent);
        }


        ImageView facebookImageView = findViewById(R.id.facebookImageView);

        facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookProfile();
            }
        });

        ImageView instagramImageView = findViewById(R.id.instagramImageView);

        instagramImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagramProfile();
            }
        });

        ImageView twitterImageView = findViewById(R.id.twitterImageView);

        twitterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitterProfile();
            }
        });

        backendManager = new BackendManager(this);

        // Gestionnaire de clic pour l'élément "Courses"
        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window_profil);
        Menu navBar = bottomNavigationView.getMenu();


        // Gestionnaire de clic pour l'élément "Courses"
        navBar.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ProfilActivity.this, ListeDeCourse.class);
            startActivity(intent);

            return true;
        });

        navBar.findItem(R.id.recette).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ProfilActivity.this, RecettesRecommendeActivity.class);
            startActivity(intent);

            return true;
        });


        buttonSupCompte = findViewById(R.id.button_supCompte);
        buttonSupCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afficherConfirmationSuppression();
            }
        });


        Button envoyerEmailButton=findViewById(R.id.button_email);
        envoyerEmailButton.setOnClickListener(new View.OnClickListener() {
            private void envoyerEmailReclamation() {

                String destinataire = "mehdi@gmail.com";


                String sujet = "Réclamation";


                String message = "Bonjour, je souhaite déposer une réclamation.";


                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:" + destinataire));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{destinataire});
                intent.putExtra(Intent.EXTRA_SUBJECT, sujet);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("message/rfc822");


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {

                    Uri gmailUri = Uri.parse("https://mail.google.com/");
                    Intent gmailIntent = new Intent(Intent.ACTION_VIEW, gmailUri);
                    if (gmailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(gmailIntent);
                    } else {

                    }
                }
            }

            @Override
            public void onClick(View view) {
                envoyerEmailReclamation();
            }
        });

        produitGaspille=findViewById(R.id.produitjetes);


     //   editTextPerempt = findViewById(R.id.perempt);

        // Restaurer la valeur de l'EditText "perempt" lors du démarrage de l'application
   //     SharedPreferences sharedPreferences_peremp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
     //   String savedPerempt = sharedPreferences_peremp.getString(PEREMPT_KEY, "3"); // "3" est la valeur par défaut
       // editTextPerempt.setText(savedPerempt);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText "perempt"
   //     editTextPerempt.addTextChangedListener(new TextWatcher() {
     //       @Override
       //     public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Avant que le texte change
         //   }

           // @Override
          //  public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant que le texte change
         //   }

            //@Override
           // public void afterTextChanged(Editable editable) {
                // Après que le texte a changé

                // Sauvegarder la nouvelle valeur automatiquement
                //String enteredPerempt = editable.toString();
              //  SharedPreferences.Editor editor = sharedPreferences_peremp.edit();
            //    editor.putString(PEREMPT_KEY, enteredPerempt);
          //      editor.apply();
        //    }
      //  });


        editTextDelaiRappel = findViewById(R.id.delai_rappel);

        // Restaurer la valeur de l'EditText "delai_rappel" lors du démarrage de l'application
        SharedPreferences sharedPreferences_rappel = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedDelaiRappel = sharedPreferences_rappel.getString(DELAI_RAPPEL_KEY, "3"); // "3" est la valeur par défaut
        editTextDelaiRappel.setText(savedDelaiRappel);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText "delai_rappel"
        editTextDelaiRappel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Avant que le texte change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant que le texte change
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Après que le texte a changé

                // Sauvegarder la nouvelle valeur automatiquement
                String enteredDelaiRappel = editable.toString();
                SharedPreferences.Editor editor = sharedPreferences_rappel.edit();
                editor.putString(DELAI_RAPPEL_KEY, enteredDelaiRappel);
                editor.apply();
            }
        });

        editTextQuantiteCri = findViewById(R.id.quantite_cri);

        // Restaurer la valeur de l'EditText "quantite_cri" lors du démarrage de l'application
        SharedPreferences sharedPreferences_quantite = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedQuantiteCri = sharedPreferences_quantite.getString(QUANTITE_CRI_KEY, "20"); // "20" est la valeur par défaut
        editTextQuantiteCri.setText(savedQuantiteCri);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText "quantite_cri"
        editTextQuantiteCri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Avant que le texte change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant que le texte change
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Après que le texte a changé

                // Sauvegarder la nouvelle valeur automatiquement
                String enteredQuantiteCri = editable.toString();
                SharedPreferences.Editor editor = sharedPreferences_quantite.edit();
                editor.putString(QUANTITE_CRI_KEY, enteredQuantiteCri);
                editor.apply();

                try {
                    UpdateRequest updateRequest = new UpdateRequest();

                    String input_value = String.valueOf(editTextQuantiteCri.getText());
                    if(input_value.isEmpty()) {
                        input_value = "0";
                    }


                    int selectedQuantiteCri = Integer.parseInt(input_value);


                    backendManager.updateQuantiteCritiqueParDefautStock((long) stockUserId, selectedQuantiteCri, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            // Traitez le succès ici si nécessaire
                        }

                        @Override
                        public void onError(Exception error) {
                            // Traitez l'erreur ici si nécessaire
                            Toast.makeText(getApplicationContext(), "Erreur lors de la mise à jour du Quantite Critique par Défaut: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        editTextTaille = findViewById(R.id.editTexte_taille);

        // Restaurer la valeur sauvegardée lors du démarrage de l'application
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedTaille = prefs.getString(TAILLE_KEY, "");
        editTextTaille.setText(savedTaille);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText
        editTextTaille.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Avant que le texte change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant que le texte change
            }

            @Override
            public void afterTextChanged(Editable editable) {

                // Sauvegarder la nouvelle valeur automatiquement
                String enteredValue = editable.toString();
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(TAILLE_KEY, enteredValue);
                editor.apply();

                String selectedTailleUnit = (String) spinnerTaille.getSelectedItem();

                // Conversion de la taille si l'unité est en mètres
                String taille = String.valueOf(editTextTaille.getText());
                if (selectedTailleUnit.equals("m")) {
                    taille = String.valueOf((int) (Double.parseDouble(taille) * 100));
                }
                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setTaille(taille);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Taille mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du Taille", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        editTextTaille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTaille.setFocusableInTouchMode(true);
                editTextTaille.requestFocus();
            }
        });

        Switch yourSwitch = findViewById(R.id.switch1);
        Switch yourSwitch2 = findViewById(R.id.switch2);
        Switch yourSwitch3 = findViewById(R.id.switch3);
        Switch yourSwitch4 = findViewById(R.id.switch4);


        yourSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch.getThumbDrawable().setTint(thumbColor);

                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setModeSportif(isChecked);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            if(isChecked) {
                                Toast.makeText(getBaseContext(), "Mode sportif est activé avec succès", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getBaseContext(), "Mode sportif est désactivé avec succès", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Exception error) {
                            if(isChecked) {
                                Toast.makeText(getBaseContext(), "Erreur lors d'activiation du Mode sportif", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getBaseContext(), "Erreur lors du désactiviation du Mode sportif", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SharedPreferences sharedPreferences_switch1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitchState = sharedPreferences_switch1.getBoolean(SWITCH_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch.setChecked(savedSwitchState);


        yourSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch2.getThumbDrawable().setTint(thumbColor);
            }
        });

        SharedPreferences sharedPreferences_switch2 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitch2State = sharedPreferences_switch2.getBoolean(SWITCH2_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch2.setChecked(savedSwitch2State);

        // Ajouter un écouteur pour le changement d'état du Switch
        yourSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Sauvegarder le nouvel état automatiquement
                SharedPreferences.Editor editor = sharedPreferences_switch2.edit();
                editor.putBoolean(SWITCH2_STATE_KEY, isChecked);
                editor.apply();

                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch2.getThumbDrawable().setTint(thumbColor);
            }
        });

        yourSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch3.getThumbDrawable().setTint(thumbColor);

                MyApp myApp = (MyApp) getApplication();
                RequestQueue queue = Volley.newRequestQueue(ProfilActivity.this);


                String url = "http://"+ BASE_URL +"/api/Utilisateur/" + currentUserId +"/alerte/"+ isChecked ;
                //   String url = "http://10.0.2.2:1111/listeCourses/" + listeCourseId + "/products/" + id;

                //String url = "http://192.168.11.100:1111/listeCourses/" + listeCourseId + "/products/" + id;
                //   String url = "http://10.0.2.2:1111/listeCourses/" + listeCourseId + "/products/" + id;

                JSONObject jsonBody = new JSONObject();


                try {
                    jsonBody.put("alertedateexpi",isChecked );

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


            }
        });

        SharedPreferences sharedPreferences_switch3 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitch3State = sharedPreferences_switch3.getBoolean(SWITCH3_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch3.setChecked(savedSwitch3State);

        // Ajouter un écouteur pour le changement d'état du Switch


        yourSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch4.getThumbDrawable().setTint(thumbColor);

                MyApp myApp = (MyApp) getApplication();
                RequestQueue queue = Volley.newRequestQueue(ProfilActivity.this);


                String url = "http://"+ BASE_URL +"/api/Utilisateur/" + currentUserId +"/alertePeremp/"+ isChecked ;
                //   String url = "http://10.0.2.2:1111/listeCourses/" + listeCourseId + "/products/" + id;

                //String url = "http://192.168.11.100:1111/listeCourses/" + listeCourseId + "/products/" + id;
                //   String url = "http://10.0.2.2:1111/listeCourses/" + listeCourseId + "/products/" + id;

                JSONObject jsonBody = new JSONObject();


                try {
                    jsonBody.put("alerteproduitfinis",isChecked );

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

            }
        });

        SharedPreferences sharedPreferences_switch4 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitch4State = sharedPreferences_switch4.getBoolean(SWITCH4_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch4.setChecked(savedSwitch4State);

        // Ajouter un écouteur pour le changement d'état du Switch
    //    yourSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      //      @Override
        //    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Sauvegarder le nouvel état automatiquement
      //          SharedPreferences.Editor editor = sharedPreferences_switch4.edit();
      //          editor.putBoolean(SWITCH4_STATE_KEY, isChecked);
      //          editor.apply();

                // Mettez à jour la couleur du pouce en fonction de l'état du switch
      //          int thumbColor = isChecked
      //                  ? getResources().getColor(R.color.switch_thumb_checked_color)
      //                  : getResources().getColor(R.color.white);

        //        yourSwitch4.getThumbDrawable().setTint(thumbColor);
      //      }
     //   });


        TextView changePass=findViewById(R.id.change_pass);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Définissez l'Intent pour passer à l'écran de destination


                Intent intent = new Intent(ProfilActivity.this, ChangerPassword.class);
                startActivity(intent);
            }
        });



        spinnerGender = findViewById(R.id.spinner_gender);
        spinnerTaille= findViewById(R.id.spinner_taille);
        spinnerPoids= findViewById(R.id.spinner_poids);
        spinnerRegime= findViewById(R.id.spinner_regime);
     //   spinnerDevise= findViewById(R.id.devise);
    //    spinnerDate= findViewById(R.id.spinner_date);
        spinnerQuantite= findViewById(R.id.spinner_mesure);
    //    spinnerPerem= findViewById(R.id.spinner_date_per);

        // Définir les options pour le Spinner (ajoutez "Genre" en tant que première entrée)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.genre,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_taille = ArrayAdapter.createFromResource(
                this,
                R.array.taille,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_poids = ArrayAdapter.createFromResource(
                this,
                R.array.poids,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_regime = ArrayAdapter.createFromResource(
                this,
                R.array.regime_speciaux,
                android.R.layout.simple_spinner_item
        );



        ArrayAdapter<CharSequence> adapter_date = ArrayAdapter.createFromResource(
                this,
                R.array.date,
                android.R.layout.simple_spinner_item
        );

        ArrayAdapter<CharSequence> adapter_mesure = ArrayAdapter.createFromResource(
                this,
                R.array.mesure,
                android.R.layout.simple_spinner_item
        );
        // Spécifier la disposition de la liste déroulante
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_taille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_poids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_regime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

  //      adapter_devise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_mesure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Appliquer l'adaptateur au Spinner
        spinnerGender.setAdapter(adapter);
        spinnerTaille.setAdapter(adapter_taille);
        spinnerPoids.setAdapter(adapter_poids);
        spinnerRegime.setAdapter(adapter_regime);
    //    spinnerDevise.setAdapter(adapter_devise);
    //    spinnerDate.setAdapter(adapter_date);
        spinnerQuantite.setAdapter(adapter_mesure);
     //   spinnerPerem.setAdapter(adapter_date);

     //   SharedPreferences sharedPreferences_perem = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
  //      int savedDatePerSelection = sharedPreferences_perem.getInt(SPINNER_DATE_PER_SELECTION_KEY, 0); // 0 est la valeur par défaut
    //    spinnerPerem.setSelection(savedDatePerSelection);

        // Ajouter un écouteur de sélection pour le spinner "date_per"
      //  spinnerPerem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //    @Override
      //      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
        //        SharedPreferences.Editor editor = sharedPreferences_perem.edit();
          //      editor.putInt(SPINNER_DATE_PER_SELECTION_KEY, position);
            //    editor.apply();
          //  }

      //      @Override
       //     public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
        //    }
       // });

        SharedPreferences sharedPreferences_mesure = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedMesureSelection = sharedPreferences_mesure.getInt(SPINNER_MESURE_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerQuantite.setSelection(savedMesureSelection);

        // Ajouter un écouteur de sélection pour le spinner "mesure"
        spinnerQuantite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_mesure.edit();
                editor.putInt(SPINNER_MESURE_SELECTION_KEY, position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });



//        SharedPreferences sharedPreferences_date1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
 //       int savedDateSelection = sharedPreferences_date1.getInt(SPINNER_DATE_SELECTION_KEY, 0); // 0 est la valeur par défaut
  //      spinnerDate.setSelection(savedDateSelection);

        // Ajouter un écouteur de sélection pour le spinner "date"
    //    spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      //      @Override
        //    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
          //      SharedPreferences.Editor editor = sharedPreferences_date1.edit();
         //       editor.putInt(SPINNER_DATE_SELECTION_KEY, position);
        //        editor.apply();
       //     }

         //  @Override
      //      public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
        //    }
        //});


      //  SharedPreferences sharedPreferences_device = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    //    int savedDeviseSelection = sharedPreferences_device.getInt(SPINNER_DEVISE_SELECTION_KEY, 0); // 0 est la valeur par défaut
  //      spinnerDevise.setSelection(savedDeviseSelection);

        // Ajouter un écouteur de sélection pour le spinner "devise"
     //   spinnerDevise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       //     @Override
         //   public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
           //     SharedPreferences.Editor editor = sharedPreferences_device.edit();
             //   editor.putInt(SPINNER_DEVISE_SELECTION_KEY, position);
               // editor.apply();
  //          }

          //  @Override
        //    public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
      //      }
    //    });


        SharedPreferences sharedPreferences_gender = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedGenderSelection = sharedPreferences_gender.getInt(SPINNER_GENDER_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerGender.setSelection(savedGenderSelection);

        // Ajouter un écouteur de sélection pour le spinner "genre"
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                UpdateRequest updatedUtilisateur = new UpdateRequest();
                String sexe = (String) parentView.getSelectedItem();
                updatedUtilisateur.setSexe(sexe);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Sexe mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du Sexe", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });


        SharedPreferences sharedPreferences_taille = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedSelection = sharedPreferences_taille.getInt(SPINNER_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerTaille.setSelection(savedSelection);



        // Ajouter un écouteur de sélection pour le spinner
        spinnerTaille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_taille.edit();
                editor.putInt(SPINNER_SELECTION_KEY, position);
                editor.apply();

                String selectedTailleUnit = (String) selectedItemView.toString();

                String taille = String.valueOf(editTextTaille.getText());
                if (selectedTailleUnit.equals("m")) {
                    taille = String.valueOf((int) (Double.parseDouble(taille) * 100));
                }


                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setPoids(taille);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Taille mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du Taille", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });


        editTextPoids = findViewById(R.id.editTexte_poids);

        // Restaurer la valeur du poids lors du démarrage de l'application
        SharedPreferences sharedPreferences_poid = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedWeight = sharedPreferences_poid.getString(WEIGHT_KEY, "");
        editTextPoids.setText(savedWeight);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText "Poids"
        editTextPoids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Avant que le texte change
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Pendant que le texte change
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Après que le texte a changé

                // Sauvegarder la nouvelle valeur automatiquement
                String enteredWeight = editable.toString();
                SharedPreferences.Editor editor = sharedPreferences_poid.edit();
                editor.putString(WEIGHT_KEY, enteredWeight);
                editor.apply();

                String selectedPoidsUnit = (String) spinnerPoids.getSelectedItem();

                // Conversion de la taille si l'unité est en mètres
                String poids = String.valueOf(editTextPoids.getText());
                if (selectedPoidsUnit.equals("tonne")) {
                    poids = String.valueOf((int) (Double.parseDouble(poids) * 1000));
                } else if (selectedPoidsUnit.equals("g")) {
                    poids = String.valueOf((int) (Double.parseDouble(poids) * 0.001));
                }

                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setPoids(poids);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Poids mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du poids", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SharedPreferences sharedPreferences_listepoid = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedPoidsSelection = sharedPreferences_listepoid.getInt(SPINNER_POIDS_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerPoids.setSelection(savedPoidsSelection);

        // Ajouter un écouteur de sélection pour le spinner "poids"
        spinnerPoids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_listepoid.edit();
                editor.putInt(SPINNER_POIDS_SELECTION_KEY, position);
                editor.apply();

                try {
                    UpdateRequest updateRequest = new UpdateRequest();

                    String selectedPoidsUnit = (String) spinnerPoids.getSelectedItem();

                    // Conversion de la taille si l'unité est en mètres
                    String poids = String.valueOf(editTextPoids.getText());
                    if (selectedPoidsUnit.equals("tonne")) {
                        poids = String.valueOf((int) ( Double.parseDouble(poids)* 1000));
                    } else if (selectedPoidsUnit.equals("g")) {
                        poids = String.valueOf((int) ( Double.parseDouble(poids)* 0.001));
                    }

                    updateRequest.setPoids(poids);

                    backendManager.updateUtilisateur((long) currentUserId, updateRequest, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Mise à jour du Poids avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            // Traitez l'erreur ici si nécessaire
                            Toast.makeText(getApplicationContext(), "Erreur lors de la mise à jour du Poids: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });

        SharedPreferences sharedPreferences_regime = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedRegimeSelection = sharedPreferences_regime.getInt(SPINNER_REGIME_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerRegime.setSelection(savedRegimeSelection);

        // Ajouter un écouteur de sélection pour le spinner "regime"
        spinnerRegime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_regime.edit();
                editor.putInt(SPINNER_REGIME_SELECTION_KEY, position);
                editor.apply();

                String selectedRegime = (String) parentView.getSelectedItem();

                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setRégimeSpécieux(selectedRegime);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Regime spécial mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du Regime spécial", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });


        pickDateButton = findViewById(R.id.date_naissance);
        date_naissace=findViewById(R.id.dateNaissance);
     

        // Mettez à jour le TextView avec la date actuelle

        SharedPreferences sharedPreferences_date = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedDate = sharedPreferences_date.getString(SELECTED_DATE_KEY, "");
        date_naissace.setText(savedDate);

        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le DatePickerDialog

                openDialog();
            }
        });

        nomProfilView = findViewById(R.id.nomProfil);
        emailProfilView = findViewById(R.id.emailProfil);

        LogoutButton = findViewById(R.id.button_deconne);

        produitCuisine = findViewById(R.id.produitencuisine);


        String url = "http://" + BASE_URL+"/stocks/" + stockUserId+ "/products";
        String url1 = "http://" + BASE_URL+"/stocks/" + stockUserId+ "/products/gaspille";
        RequestQueue queue = Volley.newRequestQueue(ProfilActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the JSON response
                            JSONArray jsonResponse = new JSONArray(response);
                            Long count= (long) jsonResponse.length();
                            // Retrieve the values from the JSON object

                            produitCuisine.setText(String.valueOf(count));


                            // Now you can use 'id' and 'intitule' as needed

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the JSON response
                            JSONArray jsonResponse = new JSONArray(response);
                            Long count= (long) jsonResponse.length();
                            // Retrieve the values from the JSON object

                            produitGaspille.setText(String.valueOf(count));


                            // Now you can use 'id' and 'intitule' as needed

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response
                error.printStackTrace();
            }
        });

        queue.add(stringRequest1);





        //Récupérer tout les informations depuis le backend d'Utilisateur
        backendManager.getUtilisateur(currentUserId, new BackendManager.BackendResponseCallback() {

            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                String nomProfil = response.getString("nom") + " " + response.getString("prénom");
                nomProfilView.setText(nomProfil);
                emailProfilView.setText(response.getString("email"));

                boolean sportif = response.getBoolean("modeSportif");
              //  Switch genderSwitch = findViewById(R.id.genderSwitch); // Assurez-vous d'avoir un élément Switch avec l'ID genderSwitch dans votre layout XML


                    if (sportif) {
                        yourSwitch.setChecked(true); // Homme est activé
                    } else  {
                        yourSwitch.setChecked(false); // Femme est activé
                    }


                boolean alertedateexpi = response.getBoolean("alertedateexpi");
                //  Switch genderSwitch = findViewById(R.id.genderSwitch); // Assurez-vous d'avoir un élément Switch avec l'ID genderSwitch dans votre layout XML


                    if (alertedateexpi) {
                        yourSwitch3.setChecked(true); // Homme est activé
                    } else  {
                        yourSwitch3.setChecked(false); // Femme est activé
                    }


                boolean alerteproduitfinis = response.getBoolean("alerteproduitfinis");
                //  Switch genderSwitch = findViewById(R.id.genderSwitch); // Assurez-vous d'avoir un élément Switch avec l'ID genderSwitch dans votre layout XML


                    if (alerteproduitfinis) {
                        yourSwitch4.setChecked(true); // Homme est activé
                    } else {
                        yourSwitch4.setChecked(false); // Femme est activé
                    }



                String gender = response.getString("sexe");
                if(!gender.equals("null")) {
                    if(gender.equals("Homme")) {
                        spinnerRegime.setSelection(0);
                    } else if(gender.equals("Femme")) {
                        spinnerRegime.setSelection(1);
                    }
                }

                String taille = response.getString("taille");
                if(!taille.equals("null")) {
                    editTextTaille.setText(taille);
                    spinnerTaille.setSelection(1);
                }

                String poids = response.getString("poids");
                if(!poids.equals("null")) {
                    editTextPoids.setText(poids);
                    spinnerPoids.setSelection(1);
                }

                String dateDeNaissance = response.getString("dateDeNaissance");
                OffsetDateTime offsetDateTime = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    offsetDateTime = OffsetDateTime.parse(dateDeNaissance, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                }

                // Define the desired output format
                DateTimeFormatter outputFormatter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    // Format the OffsetDateTime to the desired output format
                    dateDeNaissance = offsetDateTime.format(outputFormatter);

                    if(!dateDeNaissance.equals("null")) {
                        date_naissace.setText(dateDeNaissance);
                    }
                }





            }

            @Override
            public void onError(Exception error) {
                error.printStackTrace();
            }
        });


        //Récupérer tout les informations depuis le backend du Stock d'Utilisateur
        try {
            backendManager.getStock((long) stockUserId, new BackendManager.BackendResponseCallback() {

                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    String quantiteCritiqueParDefaut = response.getString("quantiteCritiqueParDefaut");

                    if(!quantiteCritiqueParDefaut.equals("null")) {
                        editTextQuantiteCri.setText(quantiteCritiqueParDefaut);
                    }

                }

                @Override
                public void onError(Exception error) {
                    error.printStackTrace();
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.setUser_id(-1);
                Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }



    private void openDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
                date_naissace.setText(selectedDate);

                // Sauvegarder la date sélectionnée
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(SELECTED_DATE_KEY, selectedDate);
                editor.apply();
                String dateDeNaissance = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(day);

                if(String.valueOf(day).length() == 1){
                    dateDeNaissance = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-0" + String.valueOf(day);
                }
                if(String.valueOf(month).length() == 1){
                    dateDeNaissance = String.valueOf(year) + "-0" + String.valueOf(month + 1) + "-0" + String.valueOf(day);
                }

                Date dateNaissance=new Date(year,month+1,day,00,00,00);

                UpdateRequest updatedUtilisateur = new UpdateRequest();
                updatedUtilisateur.setDateDeNaissance(dateDeNaissance);

                try {
                    backendManager.updateUtilisateur((long) currentUserId, updatedUtilisateur, new BackendManager.BackendResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject response) throws JSONException {
                            Toast.makeText(getBaseContext(), "Date de Naissance mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Exception error) {
                            Toast.makeText(getBaseContext(), "Erreur lors de la mise à jour du Date de Naissance", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        },year, month, day);

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profil, menu);
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Date parseDateFromString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            // Gérer l'erreur de parsing selon vos besoins
            return null;
        }
    }

    private void afficherConfirmationSuppression() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Voulez-vous vraiment supprimer votre compte?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Ajoutez le code pour supprimer le compte ici
                dialogInterface.dismiss();
                backendManager.deleteUtilisateur(currentUserId, new BackendManager.BackendResponseCallback() {

                    @Override
                    public void onSuccess(JSONObject response) throws JSONException {
                        MyApp myApp = (MyApp) getApplication();
                        myApp.setUser_id(0);
                        Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Exception error) {
                        error.printStackTrace();
                    }
                });
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Annuler la suppression
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}