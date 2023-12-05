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

public class ChangerPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_password);



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