package com.example.stockifi.corbeille;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.LoginActivity;
import com.example.stockifi.ProfilActivity;
import com.example.stockifi.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class corbeille extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corbeille);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_corbeille);

        Menu menu = toolbar.getMenu();
        MenuItem itempo = menu.findItem(R.id.poubelle);

        itempo.setEnabled(false);

        Drawable originalIcon = itempo.getIcon();
        Drawable grayedOutIcon = originalIcon.mutate();
        int disabledColor = getResources().getColor(android.R.color.darker_gray);
        grayedOutIcon.setColorFilter(disabledColor, PorterDuff.Mode.SRC_IN);
        itempo.setIcon(grayedOutIcon);

     /*   ImageView trashImageView = findViewById(R.id.trash);
        trashImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        TextView textView = findViewById(R.id.aboveListViewText);
        ImageView imageView = findViewById(R.id.trash);

        View.OnClickListener commonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        textView.setOnClickListener(commonClickListener);
        imageView.setOnClickListener(commonClickListener);
    }




}