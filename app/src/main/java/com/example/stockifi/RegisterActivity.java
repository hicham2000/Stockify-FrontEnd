package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText nomText = findViewById(R.id.nom);
        EditText prenomText = findViewById(R.id.prenom);
        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);
        EditText confirmPasswordText = findViewById(R.id.confirm_password);
        Spinner regimeOptionSpinner = findViewById(R.id.regime_option);
        EditText regimeDescriptionText = findViewById(R.id.regime_description);
        CheckBox acceptConditionCheckBox = findViewById(R.id.checkBox);
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input when the button is clicked
                String nom = prenomText.getText().toString();
                String prenom = prenomText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String confirmPassword = confirmPasswordText.getText().toString();
                String selectedRegime = (String) regimeOptionSpinner.getSelectedItem();
                String regimeDescription = regimeDescriptionText.getText().toString();
                boolean acceptCondition = acceptConditionCheckBox.isChecked();
                if (password !=password){
                    errorMessage.set("Les mots de passe ne correspondent pas",3).show();
                    passwordText.setText(" ");
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() > 0) {
            Toast.makeText(getApplicationContext(),"email format invalid",3).show();}
                if(nom.length()==0){Toast.makeText(getApplicationContext(),"Nom est un champs obligtoire",3).show();}
                if(prenom.length()==0){Toast.makeText(getApplicationContext(),"Prenom est un champs obligtoire",3).show();}
                if(email.length()==0){Toast.makeText(getApplicationContext(),"email est un champs obligtoire",3).show();}

                if(password.length()==0){Toast.makeText(getApplicationContext(),"password est un champs obligtoire",3).show();}
                if(confirmPassword.length()==0){Toast.makeText(getApplicationContext(),"confirmer le mot de pass",3).show();}

                if(selectedRegime=="Regimes specieux"){selectedRegime="Sans";}
                if(acceptCondition == false ){Toast.makeText(getApplicationContext(),"Accepter les conditions d'utilisation ",3).show();}

            }
        });

    }
}