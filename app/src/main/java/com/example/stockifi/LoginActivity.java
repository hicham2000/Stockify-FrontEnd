package com.example.stockifi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stockifi.GlobalVariables.MyApp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

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
        TextInputEditText emailTextEdit = findViewById(R.id.email_login);
        TextInputEditText passwordTextEdit = findViewById(R.id.password_login);

        MyApp myApp = (MyApp) getApplication();

        // Create an instance of BackendManager in your LoginActivity
        BackendManager backendManager = new BackendManager(this);

        boutonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() && validatePassword()) {
                    String email = emailTextEdit.getText().toString();
                    String password = passwordTextEdit.getText().toString();

                    // If email and password are valid, send a login request
                    try {
                        backendManager.login(email, password, new BackendManager.BackendCallback() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                try {
                                    // Affichez simplement la réponse pour déboguer
                                    System.out.println("Response: " + response);

                                    int userId = response.getInt("user_id");

                                    // Set the user_id in myApp
                                    myApp.setUser_id(userId);

                                    // Handle the success response from the server
                                    // For example, you can navigate to the next screen
                                    Intent intent = new Intent(LoginActivity.this, ProfilActivity.class);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Handle JSON parsing error
                                    System.out.println("JSON parsing error " + e);
                                }
                            }


                            @Override
                            public void onError(Exception error) {
                                // Handle errors, for example, display an error message
                                error.printStackTrace();
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
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
