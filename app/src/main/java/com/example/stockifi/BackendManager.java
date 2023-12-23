package com.example.stockifi;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BackendManager {

    private static final String BASE_URL = "http://192.168.1.60:1111";
    private static final String LOGIN_ENDPOINT = "/api/Login";

    private final RequestQueue requestQueue;

    public BackendManager(Context context) {
        // Initialize the RequestQueue.
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void login(String email, String password, BackendCallback callback) throws JSONException {
        // Create a JSON object with the user's credentials
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                getFullUrl(LOGIN_ENDPOINT),
                createLoginRequestBody(email, password),
                response -> {
                    callback.onSuccess(response);

                },
                error -> callback.onError(error));

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }


    private String getFullUrl(String endpoint) {
        return BASE_URL + endpoint;
    }

    private JSONObject createLoginRequestBody(String email, String password) throws JSONException {
        // Create a JSON object with the user's credentials
        // Customize this method based on your backend API requirements
        // For example:
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        return jsonRequest;
    }

    public interface BackendCallback {
        void onSuccess(JSONObject response);

        void onError(Exception error);

    }
}
