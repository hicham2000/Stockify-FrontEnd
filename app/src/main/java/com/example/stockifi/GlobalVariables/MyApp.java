package com.example.stockifi.GlobalVariables;
import com.example.stockifi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import notifications.Notification;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import notifications.Notification;

public class MyApp extends Application {

    public void onCreate() {
        final String TAG = "NOTIF";
        super.onCreate();
        Notification notif = new Notification();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();
                        setToken(token);
                        Log.d(TAG, "token"+token);

                    }
                });
    }

    private int User_id = 1;
    private int User_stock_id = 1;

    private  int User_listeCourse_id = 1;
    private String token;

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getUser_stock_id() {
        return User_stock_id;
    }

    public void setUser_stock_id(int user_stock_id) {
        User_stock_id = user_stock_id;
    }

    public int getUser_listeCourse_id() {
        return User_listeCourse_id;
    }

    public void setUser_listeCourse_id(int user_listeCourse_id) {
        User_listeCourse_id = user_listeCourse_id;
    }
    public String getToken(){
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
