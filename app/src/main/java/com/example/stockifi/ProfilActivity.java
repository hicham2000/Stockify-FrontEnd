package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stockifi.Liste_Course.ListeDeCourse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class ProfilActivity extends AppCompatActivity {

    private static final int MENU_POUBELLE = R.id.poubelle;
    private static final int MENU_MESSAGE = R.id.message;
    private static final int MENU_PROFIL = R.id.profil1;

    private static final String SWITCH3_STATE_KEY = "switch3StateKey";


    private LinearLayout pickDateButton;
    private TextView date_naissace;

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



    public void onBouttonCondiClick(View view) {
        // Code à exécuter lorsque le LinearLayout est cliqué
        Intent intent = new Intent(this, ConditionUtilisationActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Gestionnaire de clic pour l'élément "Courses"
        BottomNavigationView bottomNavigationView = findViewById(R.id.androidx_window);
        Menu menu = bottomNavigationView.getMenu();


        // Gestionnaire de clic pour l'élément "Courses"
        menu.findItem(R.id.courses).setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(ProfilActivity.this, ListeDeCourse.class);
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
                                                      // Adresse e-mail du destinataire (modifiable selon vos besoins)
                                                      String destinataire = "mehdi@gmail.com";

                                                      // Sujet du courriel (modifiable selon vos besoins)
                                                      String sujet = "Réclamation";

                                                      // Message du courriel (modifiable selon vos besoins)
                                                      String message = "Bonjour, je souhaite déposer une réclamation.";

                                                      // Créer une intention pour envoyer un e-mail via Gmail
                                                      Intent intent = new Intent(Intent.ACTION_SENDTO);
                                                      intent.setData(Uri.parse("mailto:" + destinataire));
                                                      intent.putExtra(Intent.EXTRA_SUBJECT, sujet);
                                                      intent.putExtra(Intent.EXTRA_TEXT, message);

                                                      // Vérifier si l'appareil dispose d'une application de messagerie capable de gérer cette intention
                                                      if (intent.resolveActivity(getPackageManager()) != null) {
                                                          startActivity(intent);
                                                      }
                                                  }
            @Override
            public void onClick(View view) {
                envoyerEmailReclamation();
            }
                                              });

        editTextPerempt = findViewById(R.id.perempt);

        // Restaurer la valeur de l'EditText "perempt" lors du démarrage de l'application
        SharedPreferences sharedPreferences_peremp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedPerempt = sharedPreferences_peremp.getString(PEREMPT_KEY, "3"); // "3" est la valeur par défaut
        editTextPerempt.setText(savedPerempt);

        // Ajouter un TextWatcher pour détecter les changements dans l'EditText "perempt"
        editTextPerempt.addTextChangedListener(new TextWatcher() {
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
                String enteredPerempt = editable.toString();
                SharedPreferences.Editor editor = sharedPreferences_peremp.edit();
                editor.putString(PEREMPT_KEY, enteredPerempt);
                editor.apply();
            }
        });


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
                // Après que le texte a changé

                // Sauvegarder la nouvelle valeur automatiquement
                String enteredValue = editable.toString();
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(TAILLE_KEY, enteredValue);
                editor.apply();
            }
        });

       // AppBarLayout appBarLayout = findViewById(R.id.appBar);
        MaterialToolbar toolbar = findViewById(R.id.toolbar); // Assurez-vous que le R.id.toolbar correspond à votre MaterialToolbar

        // Ajoutez ceci pour afficher le bouton de retour (optionnel)


        // Gestionnaire d'événements du menu



        final EditText editText = findViewById(R.id.editTexte_taille);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
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
            }
        });

        SharedPreferences sharedPreferences_switch1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitchState = sharedPreferences_switch1.getBoolean(SWITCH_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch.setChecked(savedSwitchState);

        // Ajouter un écouteur pour le changement d'état du Switch
        yourSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Sauvegarder le nouvel état automatiquement
                SharedPreferences.Editor editor = sharedPreferences_switch1.edit();
                editor.putBoolean(SWITCH_STATE_KEY, isChecked);
                editor.apply();
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch.getThumbDrawable().setTint(thumbColor);
            }
        });

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
            }
        });

        SharedPreferences sharedPreferences_switch3 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitch3State = sharedPreferences_switch3.getBoolean(SWITCH3_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch3.setChecked(savedSwitch3State);

        // Ajouter un écouteur pour le changement d'état du Switch
        yourSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Sauvegarder le nouvel état automatiquement
                SharedPreferences.Editor editor = sharedPreferences_switch3.edit();
                editor.putBoolean(SWITCH3_STATE_KEY, isChecked);
                editor.apply();

                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch3.getThumbDrawable().setTint(thumbColor);
            }
        });

        yourSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch4.getThumbDrawable().setTint(thumbColor);
            }
        });

        SharedPreferences sharedPreferences_switch4 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean savedSwitch4State = sharedPreferences_switch4.getBoolean(SWITCH4_STATE_KEY, false); // false est la valeur par défaut
        yourSwitch4.setChecked(savedSwitch4State);

        // Ajouter un écouteur pour le changement d'état du Switch
        yourSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Sauvegarder le nouvel état automatiquement
                SharedPreferences.Editor editor = sharedPreferences_switch4.edit();
                editor.putBoolean(SWITCH4_STATE_KEY, isChecked);
                editor.apply();

                // Mettez à jour la couleur du pouce en fonction de l'état du switch
                int thumbColor = isChecked
                        ? getResources().getColor(R.color.switch_thumb_checked_color)
                        : getResources().getColor(R.color.white);

                yourSwitch4.getThumbDrawable().setTint(thumbColor);
            }
        });


        TextView changePass=findViewById(R.id.change_pass);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Définissez l'Intent pour passer à l'écran de destination
                Intent intent = new Intent(ProfilActivity.this, ChangerPassword.class);
                startActivity(intent);
            }
        });
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Récupérer le RadioButton sélectionné
            RadioButton selectedRadioButton = findViewById(checkedId);

            // Faire quelque chose en fonction de la sélection
            if (selectedRadioButton != null) {
                String selectedText = selectedRadioButton.getText().toString();
              //  showToast("Unité sélectionnée : " + selectedText);
            }
        });

        radioButtonMetric = findViewById(R.id.radioButtonMetric);
        radioButtonImperial = findViewById(R.id.radioButtonImperial);

        // Restaurer la sélection du RadioGroup lors du démarrage de l'application
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int selectedRadioButtonId = sharedPreferences.getInt(SELECTED_RADIO_BUTTON_KEY, R.id.radioButtonMetric); // radioButtonMetric est la valeur par défaut
        radioGroup.check(selectedRadioButtonId);

        // Ajouter un écouteur de sélection pour le RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(SELECTED_RADIO_BUTTON_KEY, checkedId);
                editor.apply();
            }
        });







         spinnerGender = findViewById(R.id.spinner_gender);
         spinnerTaille= findViewById(R.id.spinner_taille);
         spinnerPoids= findViewById(R.id.spinner_poids);
         spinnerRegime= findViewById(R.id.spinner_regime);
         spinnerDevise= findViewById(R.id.devise);
         spinnerDate= findViewById(R.id.spinner_date);
         spinnerQuantite= findViewById(R.id.spinner_mesure);
         spinnerPerem= findViewById(R.id.spinner_date_per);

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

        ArrayAdapter<CharSequence> adapter_devise = ArrayAdapter.createFromResource(
                this,
                R.array.devise,
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

        adapter_devise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter_mesure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Appliquer l'adaptateur au Spinner
        spinnerGender.setAdapter(adapter);
        spinnerTaille.setAdapter(adapter_taille);
        spinnerPoids.setAdapter(adapter_poids);
        spinnerRegime.setAdapter(adapter_regime);
        spinnerDevise.setAdapter(adapter_devise);
        spinnerDate.setAdapter(adapter_date);
        spinnerQuantite.setAdapter(adapter_mesure);
        spinnerPerem.setAdapter(adapter_date);

        SharedPreferences sharedPreferences_perem = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedDatePerSelection = sharedPreferences_perem.getInt(SPINNER_DATE_PER_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerPerem.setSelection(savedDatePerSelection);

        // Ajouter un écouteur de sélection pour le spinner "date_per"
        spinnerPerem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_perem.edit();
                editor.putInt(SPINNER_DATE_PER_SELECTION_KEY, position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });

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



        SharedPreferences sharedPreferences_date1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedDateSelection = sharedPreferences_date1.getInt(SPINNER_DATE_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerDate.setSelection(savedDateSelection);

        // Ajouter un écouteur de sélection pour le spinner "date"
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_date1.edit();
                editor.putInt(SPINNER_DATE_SELECTION_KEY, position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });


        SharedPreferences sharedPreferences_device = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedDeviseSelection = sharedPreferences_device.getInt(SPINNER_DEVISE_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerDevise.setSelection(savedDeviseSelection);

        // Ajouter un écouteur de sélection pour le spinner "devise"
        spinnerDevise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_device.edit();
                editor.putInt(SPINNER_DEVISE_SELECTION_KEY, position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Ne rien faire ici
            }
        });


        SharedPreferences sharedPreferences_gender = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedGenderSelection = sharedPreferences_gender.getInt(SPINNER_GENDER_SELECTION_KEY, 0); // 0 est la valeur par défaut
        spinnerGender.setSelection(savedGenderSelection);

        // Ajouter un écouteur de sélection pour le spinner "genre"
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Sauvegarder la nouvelle sélection automatiquement
                SharedPreferences.Editor editor = sharedPreferences_gender.edit();
                editor.putInt(SPINNER_GENDER_SELECTION_KEY, position);
                editor.apply();
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


    }



    private void openDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String selectedDate = "       " + String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);
                date_naissace.setText(selectedDate);

                // Sauvegarder la date sélectionnée
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(SELECTED_DATE_KEY, selectedDate);
                editor.apply();
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

    private void afficherConfirmationSuppression() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Voulez-vous vraiment supprimer votre compte?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Ajoutez le code pour supprimer le compte ici
                dialogInterface.dismiss();
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