package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class ChangerPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_password);

        final EditText editTextPassword = findViewById(R.id.editTextPassword);
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