package com.example.stockifi;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.provider.OpenableColumns;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private  Uri selectedImageUri;
    private static final long MAX_FILE_SIZE_BYTES = 50* 1024 * 1024; // 5MB

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }



    protected void onResume() {
    super.onResume();
        ImageView imageview = findViewById(R.id.camera_icon);
        if (selectedImageUri != null) {

            Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageview);


        }



    }

    // this function hundel the Click event on the button "s'incrire "
    public void inscrire(View view) {

        EditText nomText = findViewById(R.id.nom);
        EditText prenomText = findViewById(R.id.prenom);
        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);
        EditText confirmPasswordText = findViewById(R.id.confirm_password);
        Spinner regimeOptionSpinner = findViewById(R.id.regime_option);
        EditText regimeDescriptionText = findViewById(R.id.regime_description);
        CheckBox acceptConditionCheckBox = findViewById(R.id.checkBox);
        Button registerButton = findViewById(R.id.registerButton);
        // Retrieve user input when the button is clicked
        String nom = nomText.getText().toString();
        String prenom = prenomText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();
        String selectedRegime = (String) regimeOptionSpinner.getSelectedItem();
        String regimeDescription = regimeDescriptionText.getText().toString();
        boolean acceptCondition = acceptConditionCheckBox.isChecked();
        boolean isValid = true;

        if(nom.isEmpty()){nomText.setError("Le nom est un champ obligatoire ");
        isValid = false;}else{nomText.setError(null);}
        if(prenom.isEmpty()){prenomText.setError("Prénom est un champs obligtoire");
            isValid = false;}else{prenomText.setError(null);}
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){ emailText.setError("Email est un champs obligtoire");
            isValid = false;}else{emailText.setError(null);}

        if(password.isEmpty()){passwordText.setError("Password est un champs obligtoire");
            isValid = false;}else{passwordText.setError(null);}
        if(confirmPassword.isEmpty()){
            confirmPasswordText.setError("confirmer le mot de pass");
            isValid = false;}else{confirmPasswordText.setError(null);}
        if (!password.equals(confirmPassword)){
            confirmPasswordText.setError("Confirmation ne correspond pas au mot de passe");
            isValid = false;
        }else{confirmPasswordText.setError(null);}
        if(selectedRegime=="Regimes specieux"){selectedRegime="Sans";}
        if(acceptCondition == false ){
            Toast.makeText(getApplicationContext(),"Accepter les conditions d'utilisation",Toast.LENGTH_SHORT);
            isValid = false;}

        if(isValid) {Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);}
    }


    // function handel the click on the camera icon to upload images
    public void upload_image(View view){
        photouploader();


    }
// create implement photouploader
    private void photouploader() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // create implement OnActivityResult
    protected  void   onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();

            if (selectedImageUri != null) {

                long fileSize = getFileSize(selectedImageUri);
                if (fileSize > 0 && fileSize <= MAX_FILE_SIZE_BYTES) {

                    //handleSelectedImage();
                } else {


                    selectedImageUri = null;
                }
            }
        }

    }

// get the photo size
    private long getFileSize(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
            return cursor.getLong(sizeIndex);
        }
        return 0;
    }


}