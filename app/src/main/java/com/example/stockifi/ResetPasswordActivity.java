package com.example.stockifi;

import android.os.Bundle;
        import android.view.MenuItem;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.toolbarresetPwd);
        setSupportActionBar(toolbar);

        // Enable the Up button (arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the title
        getSupportActionBar().setTitle("");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            // This is the ID of the Up button in the action bar/toolbar
            // Navigate back to LoginActivity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}