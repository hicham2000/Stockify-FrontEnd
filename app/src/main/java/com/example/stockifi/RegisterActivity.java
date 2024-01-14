package com.example.stockifi;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.provider.OpenableColumns;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private static final long MAX_FILE_SIZE_BYTES = 50 * 1024 * 1024;

    EditText nomText, prenomText, emailText, passwordText, confirmPasswordText, regimeDescriptionText;
    Spinner regimeOptionSpinner;
    CheckBox acceptConditionCheckBox;
    Button registerButton;
    String nom, prenom, email, password, confirmPassword, selectedRegime, regimeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomText = findViewById(R.id.nom);
        prenomText = findViewById(R.id.prenom);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        confirmPasswordText = findViewById(R.id.confirm_password);
        regimeOptionSpinner = findViewById(R.id.regime_option);
        regimeDescriptionText = findViewById(R.id.regime_description);
        acceptConditionCheckBox = findViewById(R.id.checkBox);
        registerButton = findViewById(R.id.registerButton);


        BackendManager backendManager = new BackendManager(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = nomText.getText().toString();
                prenom = prenomText.getText().toString();
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                confirmPassword = confirmPasswordText.getText().toString();
                selectedRegime = (String) regimeOptionSpinner.getSelectedItem();
                regimeDescription = regimeDescriptionText.getText().toString();

                if (validateInput()) {
                    RegisterRequest registerRequest = new RegisterRequest(prenom, nom, email, password, selectedRegime, false);

                    try {
                        backendManager.signup(registerRequest, new BackendManager.BackendResponseCallback() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onError(Exception error) {
                                handleSignupError(error);
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        ImageView imageView = findViewById(R.id.camera_icon);
        if (selectedImageUri != null) {
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView);
        }
    }

    private void handleSignupError(Exception error) {
        if (error instanceof VolleyError) {
            VolleyError volleyError = (VolleyError) error;
            if (volleyError.networkResponse != null && volleyError.networkResponse.statusCode == 400) {
                Toast.makeText(getApplicationContext(), "Utilisateur avec cet e-mail existe déjà!", Toast.LENGTH_SHORT).show();
            } else {
                error.printStackTrace();
            }
        } else {
            error.printStackTrace();
        }
    }


    private boolean validateInput() {
       Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[a-zA-Z])" +      // any letter
                        "(?=.*[@#$%^&+=])" +    // at least 1 special character
                        "(?=\\S+$)" +           // no white spaces
                        ".{4,}" +               // at least 4 characters
                        "$");
        boolean acceptCondition = acceptConditionCheckBox.isChecked();
        boolean isValid = true;

        if (nom.isEmpty()) {
            nomText.setError("Le nom est un champ obligatoire ");
            isValid = false;
        } else {
            nomText.setError(null);
        }

        if (prenom.isEmpty()) {
            prenomText.setError("Prénom est un champs obligatoire");
            isValid = false;
        } else {
            prenomText.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Email est un champs obligatoire");
            isValid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty()) {
            passwordText.setError("Password est un champs obligatoire");
            isValid = false;
        } else {
            if(!PASSWORD_PATTERN.matcher(password).matches()){passwordText.setError("Password must be at least 4 characters long and include at least one letter, one special character (@, #, $, %, ^, &, +), and no white spaces.");}
            else{passwordText.setError(null);}
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordText.setError("confirmer le mot de pass");
            isValid = false;
        } else {
            confirmPasswordText.setError(null);
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordText.setError("Confirmation ne correspond pas au mot de passe");
            isValid = false;
        } else {
            confirmPasswordText.setError(null);
        }

        if (selectedRegime.equals("Regimes specieux")) {
            selectedRegime = "Sans";
        }

        if (!acceptCondition) {
            Toast.makeText(getApplicationContext(), "Accepter les conditions d'utilisation", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    public void upload_image(View view) {
        photouploader();
    }

    private void photouploader() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                long fileSize = getFileSize(selectedImageUri);
                if (fileSize > 0 && fileSize <= MAX_FILE_SIZE_BYTES) {
                    // Handle selected image
                } else {
                    selectedImageUri = null;
                }
            }
        }
    }

    private long getFileSize(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
            return cursor.getLong(sizeIndex);
        }
        return 0;
    }

}



