package com.example.stockifi.GlobalVariables;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.example.stockifi.notification.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.annotation.NonNull;
public class MyApp extends Application {




    private int User_id = 1;
    private int User_stock_id = 1;
    private  String notiftoken;
    private  int User_listeCourse_id = 1;

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
