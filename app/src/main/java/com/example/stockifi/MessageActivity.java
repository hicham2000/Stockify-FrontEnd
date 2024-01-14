package com.example.stockifi;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ListView notificationListView = findViewById(R.id.notificationListView);


        try {
            InputStream inputStream = getResources().openRawResource(R.raw.notifications);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            List<String> notificationList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String title = jsonObject.getString("title");
                String message = jsonObject.getString("message");

                notificationList.add(title + "\n" + message);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.notification_item, notificationList);
            notificationListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error parsing JSON");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error loading cards");

        }
        }




}