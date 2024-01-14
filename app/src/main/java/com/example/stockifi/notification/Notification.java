package com.example.stockifi.notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.ProduitCardActivity;

import org.json.JSONException;
import org.json.JSONObject;
public class Notification {

    private static final String BASE_URL = "http://192.168.11.100:1111";
    private int id;
    private int type;
    private String title;
    private String body;

    public void sendNotificationToken(Context context , String token ,int  userId){
        String url = "http://192.168.11.100:1111/api/users/" + userId + "/updateFcmToken";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("token", token);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                    response -> {

                        Log.d("TokenUpdate", "Notification token updated successfully");
                    },
                    error -> {

                        Log.e("TokenUpdate", "Error updating notification token: " + error.toString());

                    });

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
/*
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
