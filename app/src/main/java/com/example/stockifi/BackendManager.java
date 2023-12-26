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
    private static final String ENDPOINT = "/api";

    private final RequestQueue requestQueue;

    public BackendManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void login(String email, String password, BackendResponseCallback callback) throws JSONException {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                getFullUrl(ENDPOINT + "/Login"),
                createLoginRequestBody(email, password),
                response -> {
                    if (response != null && response.length() > 0) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // If not, handle the error
                        callback.onError(new JSONException("Invalid JSON response"));
                    }
                },
                callback::onError);


        requestQueue.add(jsonObjectRequest);
    }


    private String getFullUrl(String endpoint) {
        return BASE_URL + endpoint;
    }

    private JSONObject createLoginRequestBody(String email, String password) throws JSONException {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        return jsonRequest;
    }

    public void signup(RegisterRequest registerRequest, BackendResponseCallback callback) throws JSONException {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                getFullUrl(ENDPOINT + "/signup"),
                createSignupRequestBody(registerRequest),
                response -> {
                    if (response != null && response.length() > 0) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // If not, handle the error
                        callback.onError(new JSONException("Invalid JSON response"));
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    private JSONObject createSignupRequestBody(RegisterRequest registerRequest) throws JSONException {
        JSONObject jsonRequest = new JSONObject();

        jsonRequest.put("prénom", registerRequest.getPrénom());
        jsonRequest.put("nom", registerRequest.getNom());
        jsonRequest.put("email", registerRequest.getEmail());
        jsonRequest.put("password", registerRequest.getPassword());
        jsonRequest.put("régimeSpécieux", registerRequest.getRégimeSpécieux());
        jsonRequest.put("modeSportif", registerRequest.isModeSportif());

        return jsonRequest;
    }

    public void getUtilisateur(int id, BackendResponseCallback callback) {
        String url = getFullUrl(ENDPOINT + "/Utilisateur/" + id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    public void deleteUtilisateur(int id, BackendResponseCallback callback) {
        String url = getFullUrl(ENDPOINT + "/Utilisateurs/" + id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }


    public interface BackendResponseCallback {
        void onSuccess(JSONObject response) throws JSONException;

        void onError(Exception error);

    }
}
