package com.example.stockifi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ImageView toolbarBackButton_ajout = findViewById(R.id.toolbar_back_button_ajout);

        // Ajoutez un écouteur de clic à l'ImageView
        toolbarBackButton_ajout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                // Appel de la méthode onBackPressed pour revenir à l'écran précédent
                onBackPressed();
            }
        });

        ListView listView = findViewById(R.id.notificationListView);

        List<HashMap<String, String>> notificationList = loadNotificationData();

        String[] from = {"message"};
        int[] to = { R.id.message};
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                notificationList,
                R.layout.notification_item,
                from,
                to
        );

        listView.setAdapter(adapter);
        listView.setDividerHeight(0);

    }
        private List<HashMap<String, String>> loadNotificationData() {
            List<HashMap<String, String>> list = new ArrayList<>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.notifications);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = jsonObject.getString("title");
                String message = jsonObject.getString("message");

                HashMap<String, String> notification = new HashMap<>();

                notification.put("message", title+ '\n'+ message);

                list.add(notification);

            }



        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error parsing JSON");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error loading cards");

        }

        return list;}
        }

