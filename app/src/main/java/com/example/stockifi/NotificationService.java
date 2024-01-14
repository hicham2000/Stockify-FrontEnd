package com.example.stockifi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.stockifi.GlobalVariables.MyApp;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.example.stockifi.R;
import com.google.firebase.messaging.FirebaseMessaging;

import static android.content.ContentValues.TAG;


import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;

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

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

        }
    }

}



