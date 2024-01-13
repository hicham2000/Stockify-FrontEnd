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
/*
    private static final String TAG = "NotificationTest";
    private static final String TAG2 = "getToken";

    @Override
    public void onCreate() {
        super.onCreate();


    }
    @Override
        public void onNewToken(String token) {
            Log.d(TAG, "Refreshed token: " + token);

        }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }


    }

    private void sendNotification(String messageBody) {
        long notificationId = System.currentTimeMillis();
        Intent intent = new Intent(this, ProduitCardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApp.DEFAULT_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(com.google.firebase.messaging.R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Your App Name")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0, notificationBuilder.build());
    }

*/

    }



