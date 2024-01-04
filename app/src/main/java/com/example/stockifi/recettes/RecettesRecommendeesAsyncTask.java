package com.example.stockifi.recettes;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.stockifi.BackendManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RecettesRecommendeesAsyncTask extends AsyncTask<Long, Void, JSONObject> {

    private BackendManager.BackendResponseCallback callback;
    private RequestQueue requestQueue;
    private String endpoint;
    private String baseUrl;

    public RecettesRecommendeesAsyncTask(String baseUrl, BackendManager.BackendResponseCallback callback, RequestQueue requestQueue, String endpoint) {
        this.baseUrl = baseUrl;
        this.callback = callback;
        this.requestQueue = requestQueue;
        this.endpoint = endpoint;
    }

    private String getFullUrl(String endpoint) {
        return baseUrl + endpoint;
    }

    @Override
    protected JSONObject doInBackground(Long... params) {
        long userId = params[0];
        String url = getFullUrl(endpoint + "/recommendations/Recettes/" + userId);

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                future,
                future);

        System.out.println("jsonObjectRequest => " + jsonObjectRequest.getBody());

        requestQueue.add(jsonObjectRequest);

        try {
            return future.get(); // This will block until the request is complete
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        if (response != null) {
            try {
                callback.onSuccess(response);
            } catch (JSONException e) {
                callback.onError(e);
            }
        } else {
            callback.onError(new Exception("Failed to get response"));
        }
    }
}
