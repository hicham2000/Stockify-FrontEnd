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
    String currentUserPassword;

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

        int currentUserId = myApp.getUser_id();

        backendManager.getUtilisateur(currentUserId, new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                currentUserPassword = response.getString("password");
                System.out.println("currentUserPassword = " + currentUserPassword);
            }

            @Override
            public void onError(Exception error) {
                // Traitez l'erreur ici si nécessaire
                Toast.makeText(getApplicationContext(), "Erreur lors de la récupération du Mot de passe courant: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validateInput()) {
                    UpdateRequest updateRequest = new UpdateRequest();

                    String new_password = textInputLayoutpass_nouv.getEditText().getText().toString().trim();
                    updateRequest.setPassword(new_password);

                    try{
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
        } else if(currentUserPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Le Mot de passe n'est pas bien récupérer", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else if(!current_password.equals(currentUserPassword)) {
            textInputLayoutpass_courant.setError("entrer le bon mot de passe");
            isValid = false;
        }

        return isValid;
    }
}