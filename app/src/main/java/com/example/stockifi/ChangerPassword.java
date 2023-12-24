package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.example.stockifi.GlobalVariables.MyApp;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ChangerPassword extends AppCompatActivity {

    private BackendManager backendManager;
    private MyApp myApp;

    ImageView toolbarBackButton;
    TextInputLayout textInputLayoutpass_courant;
    TextInputLayout textInputLayoutpass_nouv;
    TextInputLayout textInputLayoutpass_conf;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_password);

        backendManager = new BackendManager(this);

        myApp = (MyApp) getApplication();

        toolbarBackButton = findViewById(R.id.toolbar_back_button);
        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        textInputLayoutpass_courant = findViewById(R.id.text_input_password);
        textInputLayoutpass_courant.setHintEnabled(false);

        // Disable hint animation for password
        textInputLayoutpass_nouv = findViewById(R.id.text_input_password2);
        textInputLayoutpass_nouv.setHintEnabled(false);

        textInputLayoutpass_conf = findViewById(R.id.text_input_password3);
        textInputLayoutpass_conf.setHintEnabled(false);

        submitButton = findViewById(R.id.button_email);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validateInput()) {
                    try {
                        int currentUserId = myApp.getUser_id();
                        UpdateRequest updateRequest = new UpdateRequest();

                        String new_password = textInputLayoutpass_nouv.getEditText().getText().toString().trim();

                        updateRequest.setPassword(new_password);

                        backendManager.updateUtilisateur((long) currentUserId, updateRequest, new BackendManager.BackendResponseCallback() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                onBackPressed();
                            }

                            @Override
                            public void onError(Exception error) {
                                // Traitez l'erreur ici si nécessaire
                                Toast.makeText(getApplicationContext(), "Erreur lors de la mise à jour du Date de Password: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });






     //   final ToggleButton toggleButtonShowPassword = findViewById(R.id.toggleButtonShowPassword);

  //      toggleButtonShowPassword.setOnClickListener(new View.OnClickListener() {
    //        @Override
      //      public void onClick(View view) {
        //        if (toggleButtonShowPassword.isChecked()) {
                    // Afficher le mot de passe
          //          editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            //    } else {
                    // Masquer le mot de passe
              //      editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
              //  }
         //   }
       // });
    }


    private boolean validateInput() {
        String current_password = textInputLayoutpass_courant.getEditText().getText().toString().trim();
        String new_password = textInputLayoutpass_nouv.getEditText().getText().toString().trim();
        String conf_password = textInputLayoutpass_conf.getEditText().getText().toString().trim();

        boolean isValid = true;

        System.out.println("textInputLayoutpass_conf === " + textInputLayoutpass_conf);

        if (current_password.isEmpty()){
            textInputLayoutpass_courant.setError("Cette champ ne doit pas etre vide");
            isValid = false;
        } else if (new_password.isEmpty()){
            textInputLayoutpass_nouv.setError("Cette champ ne doit pas etre vide");
            isValid = false;
        } else if (conf_password.isEmpty()){
            textInputLayoutpass_conf.setError("Cette champ ne doit pas etre vide");
            isValid = false;
        } else if(!new_password.equals(conf_password)){
            textInputLayoutpass_conf.setError("Cette champ ne correspond pas à Nouveau Mot de Passe");
            isValid = false;
        }

        return isValid;
    }
}