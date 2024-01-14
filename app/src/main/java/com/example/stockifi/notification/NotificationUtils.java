package com.example.stockifi.notification;
import android.content.Context;
import android.util.Log;
import android.database.SQLException;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;

import com.example.stockifi.GlobalVariables.MyApp;
import com.example.stockifi.MessageActivity;
import com.example.stockifi.sqlite.DatabaseHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.app.PendingIntent;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.stockifi.R;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.app.NotificationChannel;

public class NotificationUtils {
    private static final String CHANNEL_ID = "default_channel";
    private static final String BASE_URL = "http://192.168.11.100:1111+/api/users/";
    private int id;
    private int type;
    private String title;
    private String body;


    private DatabaseHelper dbHelper;
    private Context context;

    public NotificationUtils(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public void sendNotificationToken(Context context, String token, int userId) {
        String url = BASE_URL + userId + "/updateFcmToken";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("token", token);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                    response -> Log.d("TokenUpdate", "NotificationUtils token updated successfully"),
                    error -> Log.e("TokenUpdate", "Error updating notification token: " + error.toString()));

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isNotificationInDatabase(String title, String body) {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {DatabaseHelper.COLUMN_TITLE, DatabaseHelper.COLUMN_BODY};
            String selection = DatabaseHelper.COLUMN_TITLE + " = ? AND " + DatabaseHelper.COLUMN_BODY + " = ?";
            String[] selectionArgs = {title, body};

            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            int count = cursor.getCount();
            cursor.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addToDatabase(String title, String body) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_TITLE, title);
            values.put(DatabaseHelper.COLUMN_BODY, body);
            values.put(DatabaseHelper.COLUMN_TIMESTAMP, System.currentTimeMillis());

            db.insert(DatabaseHelper.TABLE_NAME, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendNotification(String messageBody, String title, Context context) {
        MyApp myApp = new MyApp();
        myApp.createNotificationChannel(context);

        String CHANNEL_ID = myApp.DEFAULT_NOTIFICATION_CHANNEL_ID;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}