package com.example.stockifi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockifi.sqlite.DatabaseHelper;
import com.example.stockifi.notification.NotificationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        ListView listView = findViewById(R.id.notificationListView);

        List<NotificationModel> notificationList = loadNotificationData();
        List<HashMap<String, String>> list = convertNotificationListToHashMap(notificationList);

        String[] from = {"message"};
        int[] to = {R.id.message};
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.notification_item,
                from,
                to
        );

        listView.setAdapter(adapter);
        listView.setDividerHeight(0);
    }

    private List<NotificationModel> loadNotificationData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllNotifications();
    }

    private List<HashMap<String, String>> convertNotificationListToHashMap(List<NotificationModel> notificationList) {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (NotificationModel notification : notificationList) {
            HashMap<String, String> notificationMap = new HashMap<>();
            notificationMap.put("message", notification.getTitle() + '\n' + notification.getBody());
            list.add(notificationMap);
        }
        return list;
    }
}
