package com.example.stockifi;
import android.util.Log;
import com.example.stockifi.notification.NotificationUtils;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "NotificationTest";
    private static final String TAG2 = "getToken";

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext()) ;
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

                    notificationUtils.addToDatabase(title, body);
                    notificationUtils.sendNotification(body,title,getApplicationContext());


        }
    }

}