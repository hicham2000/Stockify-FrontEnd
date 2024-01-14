package com.example.stockifi.GlobalVariables;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
public class MyApp extends Application {

    private int User_id = 1;
    private int User_stock_id = 1;
    private  String notiftoken;
    private  int User_listeCourse_id = 1;

    private boolean alerte_date_expiration;
    public static final String DEFAULT_NOTIFICATION_CHANNEL_ID = "default_channel";

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(getApplicationContext());
        }
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My App Channel";
            String description = "This is the default channel for My App";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(DEFAULT_NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public boolean isAlerte_date_expiration() {
        return alerte_date_expiration;
    }

    public void setAlerte_date_expiration(boolean alerte_date_expiration) {
        this.alerte_date_expiration = alerte_date_expiration;
    }

    public boolean isAlerte_produits_perissable() {
        return alerte_produits_perissable;
    }

    public void setAlerte_produits_perissable(boolean alerte_produits_perissable) {
        this.alerte_produits_perissable = alerte_produits_perissable;
    }

    private boolean alerte_produits_perissable;

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
    public  String getNotiftoken(){
        return  this.notiftoken;
    }

    public void setNotiftoken(String notiftoken) {
        this.notiftoken = notiftoken;
    }




}
