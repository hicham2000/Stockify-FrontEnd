package com.example.stockifi;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class BackendManager {


    //private static final String BASE_URL = "http://100.89.18.54:1111";
    private static final String BASE_URL = "http://10.0.2.2:1111";

    //private static final String BASE_URL = "http://192.168.3.27:1111";

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

    public void updateUtilisateur(Long id, UpdateRequest updatedUtilisateur, BackendResponseCallback callback) throws JSONException {
        String url = getFullUrl(ENDPOINT + "/Utilisateur/" + id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                createUpdateUtilisateurRequestBody(updatedUtilisateur),
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    private JSONObject createUpdateUtilisateurRequestBody(UpdateRequest updatedUtilisateur) throws JSONException {
        JSONObject updatedUserJson = new JSONObject();
        try {
            if (updatedUtilisateur.getPrénom() != null) {
                updatedUserJson.put("prenom", updatedUtilisateur.getPrénom());
            }

            if (updatedUtilisateur.getNom() != null) {
                updatedUserJson.put("nom", updatedUtilisateur.getNom());
            }

            if (updatedUtilisateur.getEmail() != null) {
                updatedUserJson.put("email", updatedUtilisateur.getEmail());
            }

            if (updatedUtilisateur.getPassword() != null) {
                updatedUserJson.put("password", updatedUtilisateur.getPassword());
            }

            if (updatedUtilisateur.getRégimeSpécieux() != null) {
                updatedUserJson.put("régimeSpécieux", updatedUtilisateur.getRégimeSpécieux());
            }

            updatedUserJson.put("modeSportif", updatedUtilisateur.isModeSportif());

            if (updatedUtilisateur.getSexe() != null) {
                updatedUserJson.put("sexe", updatedUtilisateur.getSexe());
            }

            if (updatedUtilisateur.getTaille() != null) {
                updatedUserJson.put("taille", updatedUtilisateur.getTaille());
            }

            if (updatedUtilisateur.getPoids() != null) {
                updatedUserJson.put("poids", updatedUtilisateur.getPoids());
            }

            if (updatedUtilisateur.getDateDeNaissance() != null) {
                // Assume that "dateDeNaissance" is a string in the correct format
                updatedUserJson.put("dateDeNaissance", updatedUtilisateur.getDateDeNaissance());
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return updatedUserJson;
    }

    public void updateQuantiteCritiqueParDefautStock(Long stockid, int quantiteCritiqueParDefautStock, BackendResponseCallback callback) throws JSONException {
        String url = getFullUrl("/stocks/"+stockid+"/" + quantiteCritiqueParDefautStock);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    public void getStock(Long stockid, BackendResponseCallback callback) throws JSONException {
        String url = getFullUrl("/stocks/"+stockid);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    public void recupererUnProduitFromCorbeille(long stockId,long productId, BackendResponseCallback callback){
        String url = getFullUrl( "/corbeille/recupererdeletedproduct/stockId="+stockId+"/recupererProductId="+productId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }
    public void supprimerDefUnProduitFromCorbeille(long stockId,long productId, BackendResponseCallback callback){
        String url = getFullUrl( "/corbeille/supprmerdefdeletedproduct/stockId="+stockId+"/supprimerProductId="+productId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }
    public void supprimerDefUnRepasFromCorbeille(long stockId,long productId, BackendResponseCallback callback){
        String url = getFullUrl( "/corbeille/supprmerdefdeletedrecipe/stockId="+stockId+"/supprimerRepasId="+productId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    public void recupererRecettesRecommendees(long userId, @NonNull BackendResponseCallback callback) {
        String url = getFullUrl(ENDPOINT + "/recommendations/Recettes/" + userId);

        int timeout = 10000;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


        requestQueue.add(jsonObjectRequest);
    }

    public void recupererRecettesRecommendeesFiltrees(long userId, String filterValuesJson, @NonNull BackendResponseCallback callback) {
        String url = getFullUrl(ENDPOINT + "/recommendations/RecettesFiltred/" + userId);
        JSONObject request = new JSONObject();

        int timeout = 10000;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                request,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


        requestQueue.add(jsonObjectRequest);
    }


    public void ajouterRecetteAuFavoris(long userId, long recetteId, BackendResponseCallback callback){
        String url = getFullUrl( ENDPOINT + "/Utilisateur/"+userId+"/recetteFavoris/"+recetteId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                callback::onError);

        requestQueue.add(jsonObjectRequest);
    }

    public void supprimerRecetteDeFavoris(long userId,long recetteId, BackendResponseCallback callback){
        String url = getFullUrl( ENDPOINT + "/Utilisateurs/" + userId + "/recetteFavoris/"+ recetteId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                response -> {
                    try {
                        callback.onSuccess(response);
                    } catch (JSONException e) {
                        callback.onError(e);
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
