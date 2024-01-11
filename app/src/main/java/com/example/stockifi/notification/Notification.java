package com.example.stockifi.notification;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
public class Notification {

    private int id;
    private int type;
    private String title;
    private String body;

    public void sendNotificationToken(Context context , String token ,int  userId){
        String url = "http://10.0.2.2/api/users/" + userId + "/updateFcmToken";
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

}
