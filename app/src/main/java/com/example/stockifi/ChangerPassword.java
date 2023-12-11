package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

public class ChangerPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_password);

        TextInputLayout textInputLayoutpass_courant = findViewById(R.id.text_input_password);
        textInputLayoutpass_courant.setHintEnabled(false);

        // Disable hint animation for password
        TextInputLayout textInputLayoutpass_nouv = findViewById(R.id.text_input_password2);
        textInputLayoutpass_nouv.setHintEnabled(false);

        TextInputLayout textInputLayoutpass_conf = findViewById(R.id.text_input_password3);
        textInputLayoutpass_conf.setHintEnabled(false);


        ImageView toolbarBackButton = findViewById(R.id.toolbar_back_button);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
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
}