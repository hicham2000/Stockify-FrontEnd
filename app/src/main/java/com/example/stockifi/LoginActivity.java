package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      // any letter
                    "(?=.*[@#$%^&+=])" +    // at least 1 special character
                    "(?=\\S+$)" +           // no white spaces
                    ".{4,}" +               // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button boutonLogin = findViewById(R.id.button_login);
        TextView to_inscription = findViewById(R.id.textViewCreateCompte);

        boutonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() && validatePassword()) {
                    // If email and password are valid, open the profile screen
                    Intent intent = new Intent(LoginActivity.this, ProfilActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        TextView textViewForgottenPwd = findViewById(R.id.textViewForgottenPwd);
        textViewForgottenPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        to_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);



            }
        });

        // Disable hint animation for email
        TextInputLayout textInputLayoutEmail = findViewById(R.id.text_input_email);
        textInputLayoutEmail.setHintEnabled(false);

        // Disable hint animation for password
        TextInputLayout textInputLayoutPassword = findViewById(R.id.text_input_password);
        textInputLayoutPassword.setHintEnabled(false);
    }

    private boolean validateEmail() {
        TextInputLayout textInputLayoutEmail = findViewById(R.id.text_input_email);
        String emailInput = textInputLayoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputLayoutEmail.setError("Email field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputLayoutEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        TextInputLayout textInputLayoutPassword = findViewById(R.id.text_input_password);
        String passwordInput = textInputLayoutPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputLayoutPassword.setError("Password field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputLayoutPassword.setError("Password must be at least 4 characters long and include at least one letter, one special character (@, #, $, %, ^, &, +), and no white spaces.");
            return false;
        } else {
            textInputLayoutPassword.setError(null);
            return true;
        }
    }
}
