package com.example.stockifi;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class BackendManagerTest {

    //Login Methode
    @Test
    public void testLogin_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        // Prepare expected response
        JSONObject expectedResponse = new JSONObject();
        try {
            expectedResponse.put("user_id", 123);
            expectedResponse.put("stock_id", 456);
            // Add other expected values
        } catch (JSONException e) {
            fail("Failed to create expected response");
        }

        // Create a callback for testing
        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                // Verify that the response matches the expected response
                assertEquals(expectedResponse.toString(), response.toString());
            }

            @Override
            public void onError(Exception error) {
                // Test fails if there is an error
                fail("Error callback should not be invoked");
            }
        };

        try {
            // Call the login method and pass the callback
            backendManager.login("test@example.com", "password", callback);
        } catch (JSONException e) {
            fail("Exception during login");
        }
    }
    @Test
    public void testLogin_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        // Create a callback for testing
        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                // Test fails if onSuccess is invoked for an error case
                fail("onSuccess should not be invoked for error case");
            }

            @Override
            public void onError(Exception error) {
                // Verify that the correct error type is received
                assertEquals(JSONException.class, error.getClass());
            }
        };

        try {
            // Call the login method with incorrect parameters to simulate an error
            backendManager.login("incorrectEmail", "incorrectPassword", callback);
        } catch (JSONException e) {
            // Expected exception for incorrect parameters
        }
    }

    //Signup Methode
    @Test
    public void testSignup_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        JSONObject expectedResponse = new JSONObject();
        try {
            expectedResponse.put("message", "You are registred Successfully!");
        } catch (JSONException e) {
            fail("Failed to create expected response");
        }

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertEquals(expectedResponse.toString(), response.toString());
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        try {
            RegisterRequest registerRequest = new RegisterRequest(
                    "reda","rifay",
                    "rifayreda@gmail.com","123456@Reda",
                    "", false);
            backendManager.signup(registerRequest, callback);
        } catch (JSONException e) {
            fail("Exception during signup");
        }
    }
    @Test
    public void testSignup_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        JSONObject expectedResponse = new JSONObject();
        try {
            expectedResponse.put("message", "Utilisateur with this email already exists!");
        } catch (JSONException e) {
            fail("Failed to create expected response");
        }
        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess should not be invoked for error case");
            }

            @Override
            public void onError(Exception error) {
                assertEquals(JSONException.class, error.getClass());
            }
        };

        try {
            RegisterRequest invalidRegisterRequest = new RegisterRequest(
                    "wassim", "rifay",
                    "rifaywassim@gmail.com", "123456@Wassim",
                    "", false);
            backendManager.signup(invalidRegisterRequest, callback);
        } catch (JSONException e) {
        }
    }

    //getUtilisateur Methode
    @Test
    public void testGetUtilisateur_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        int userId = 1;

        String expectedUrl = "http://192.168.1.17:1111/api/Utilisateur/1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("id"));
                assertEquals(userId, response.getInt("id"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.getUtilisateur(userId, callback);
    }
    @Test
    public void testGetUtilisateur_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        int invalidUserId = -1;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess should not be invoked for error case");
            }

            @Override
            public void onError(Exception error) {
                assertEquals(JSONException.class, error.getClass());
            }
        };
        backendManager.getUtilisateur(invalidUserId, callback);
    }

    //deleteUtilisateur Methode
    @Test
    public void testDeleteUtilisateur_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        int userId = 5;

        String expectedUrl = "http://192.168.1.17:1111/api/Utilisateurs/5";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("User Deleted successfully!...", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                // Le test échoue s'il y a une erreur
                fail("Error callback should not be invoked");
            }
        };

        backendManager.deleteUtilisateur(userId, callback);
    }
    @Test
    public void testDeleteUtilisateur_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        int userId = -1;

        String expectedUrl = "http://192.168.1.17:1111/api/Utilisateurs/-1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };
        backendManager.deleteUtilisateur(-1, callback);
    }

    //updateUtilisateur Methode
    @Test
    public void testUpdateUtilisateur_Success() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long userId = 1L;

        UpdateRequest updateRequest = new UpdateRequest();

        String expectedUrl = "http://192.168.1.17:1111/api/Utilisateur/1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("User updated successfully!...", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.updateUtilisateur(userId, updateRequest, callback);
    }
    @Test
    public void testUpdateUtilisateur_Error() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long userId = -1L;

        UpdateRequest updateRequest = new UpdateRequest();

        String expectedUrl = "http://192.168.1.17:1111/api/Utilisateur/-1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.updateUtilisateur(-1L, updateRequest, callback);
    }

    //updateQuantiteCritiqueParDefautStock Methode
    @Test
    public void testUpdateQuantiteCritiqueParDefautStock_Success() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = 1L;

        int newQuantiteCritiqueParDefaut = 200;

        String expectedUrl = "http://192.168.1.17:1111/api/stocks/1/200";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("QuantiteCritiqueParDefault du stock with stock_id=1 updated successfully!...", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.updateQuantiteCritiqueParDefautStock(stockId, newQuantiteCritiqueParDefaut, callback);
    }
    @Test
    public void testUpdateQuantiteCritiqueParDefautStock_Error() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = -1L;

        int newQuantiteCritiqueParDefaut = 200;

        String expectedUrl = "http://192.168.1.17:1111/api/stocks/-1/200";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.updateQuantiteCritiqueParDefautStock(-1L, newQuantiteCritiqueParDefaut, callback);
    }

    //getStock Methode
    @Test
    public void testGetStock_Success() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = 1L;

        String expectedUrl = "http://192.168.1.17:1111/api/stocks/1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("id"));
                assertEquals(1L, response.getLong("id"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.getStock(stockId, callback);
    }

    @Test
    public void testGetStock_Error() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = -1L;

        String expectedUrl = "http://192.168.1.17:1111/api/stocks/-1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.getStock(stockId, callback);
    }

    //recupererUnProduitFromCorbeille Methode
    @Test
    public void testRecupererUnProduitFromCorbeille_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = 1L;

        Long productId = 1L;

        String expectedUrl = "http://192.168.1.17:1111/api/corbeille/recupererdeletedproduct/stockId=1/recupererProductId=1";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Product is updated", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                // Le test échoue s'il y a une erreur
                fail("Error callback should not be invoked");
            }
        };

        backendManager.recupererUnProduitFromCorbeille(stockId, productId, callback);
    }

    @Test
    public void testRecupererUnProduitFromCorbeille_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = -1L;

        Long productId = -1L;

        String expectedUrl = "http://192.168.1.17:1111/api/corbeille/recupererdeletedproduct/stockId=-1/recupererProductId=-1";


        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.recupererUnProduitFromCorbeille(stockId, productId, callback);
    }

    //restoreProductFromCorbeille Methode
    @Test
    public void testRestoreProductFromCorbeille_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = 1L;

        Long productId = 1L;

        double quantity = 50.0;

        String expectedUrl = "http://192.168.1.17:1111/api/corbeille/recupererdeletedproduct/stockId=1/recupererProductId=1/quantity=50";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Product is restored with the new quantity", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.restoreProductFromCorbeille(stockId, productId, quantity, callback);
    }

    @Test
    public void testRestoreProductFromCorbeille_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        Long stockId = 1L;

        Long productId = 1L;

        double quantity = 50.0;

        String expectedUrl = "http://192.168.1.17:1111/api/corbeille/recupererdeletedproduct/stockId=1/recupererProductId=1/quantity=50";


        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.restoreProductFromCorbeille(stockId, productId, quantity, callback);
    }

    //supprimerDefPermanentlyUnProduitFromCorbeille Methode
    @Test
    public void testSupprimerDefPermanentlyUnProduitFromCorbeille_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long productId = 1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Product is deleted permanently", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.supprimerDefPermanentlyUnProduitFromCorbeille(stockId, productId, callback);
    }
    @Test
    public void testSupprimerDefPermanentlyUnProduitFromCorbeille_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long productId = -1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.supprimerDefPermanentlyUnProduitFromCorbeille(stockId, productId, callback);
    }

    //supprimerDefPermanentlyUnRepasFromCorbeille Methode
    @Test
    public void testSupprimerDefPermanentlyUnRepasFromCorbeille_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long repasId = 1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Repas is deleted permanently", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.supprimerDefPermanentlyUnRepasFromCorbeille(stockId, repasId, callback);
    }
    @Test
    public void testSupprimerDefPermanentlyUnRepasFromCorbeille_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long repasId = -1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.supprimerDefPermanentlyUnRepasFromCorbeille(stockId, repasId, callback);
    }

    //recupererDeletedRepasFromCorbeille Methode
    @Test
    public void testRecupererDeletedRepasFromCorbeille_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long recupererRepasId = 1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Repas:colonne isDeletd is updated", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.recupererDeletedRepasFromCorbeille(stockId, recupererRepasId, callback);
    }

    @Test
    public void testRecupererDeletedRepasFromCorbeille_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long recupererRepasId = -1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.recupererDeletedRepasFromCorbeille(stockId, recupererRepasId, callback);
    }

    //recupererRecettesRecommendees Methode
    @Test
    public void testRecupererRecettesRecommendees_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = 1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recettes Recommendées", response.getString("message"));
                assertNotNull(response.getJSONArray("recettes"));
                assertEquals(6, response.getJSONArray("recettes").length());
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.recupererRecettesRecommendees(userId, callback);
    }
    @Test
    public void testRecupererRecettesRecommendees_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = -1L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.recupererRecettesRecommendees(userId, callback);
    }

    //recupererRecettesRecommendeesFiltrees Methode
    @Test
    public void testRecupererRecettesRecommendeesFiltrees_Success() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = 1L;
        String filterValuesJson = "{\"regimeSpeciaux\": [], \"nomsDesIngredientPreferes\": [\"Onion\"], \"tempsDePreparation\": \"\"}";

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recettes Recommendées", response.getString("message"));
                assertNotNull(response.getJSONArray("recettes"));
                assertEquals(6, response.getJSONArray("recettes").length());
            }

            @Override
            public void onError(Exception error) {
                // Le test échoue s'il y a une erreur
                fail("Error callback should not be invoked");
            }
        };

        // Exécutez la méthode recupererRecettesRecommendeesFiltrees et passez le callback
        backendManager.recupererRecettesRecommendeesFiltrees(userId, filterValuesJson, callback);
    }

    @Test
    public void testRecupererRecettesRecommendeesFiltrees_Error() throws JSONException {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = -1L;
        String filterValuesJson = "{\"regimeSpeciaux\": [], \"nomsDesIngredientPreferes\": [\"Onion\"], \"tempsDePreparation\": \"\"}";

        // Créez le callback pour gérer l'erreur
        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                // Le test échoue s'il y a une réponse réussie
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                // Assurez-vous que la méthode onError est appelée pour un scénario d'erreur
                assertNotNull(error);
                // Ajoutez d'autres vérifications spécifiques si nécessaire
            }
        };

        // Exécutez la méthode recupererRecettesRecommendeesFiltrees et passez le callback
        backendManager.recupererRecettesRecommendeesFiltrees(userId, filterValuesJson, callback);
    }

    //recupererRecettesSimilaires Methode
    @Test
    public void testRecupererRecettesSimilaires_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = 1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recettes similaires", response.getString("message"));
                assertNotNull(response.getJSONArray("recettes"));
                assertEquals(3, response.getJSONArray("recettes").length());
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.recupererRecettesSimilaires(userId, recetteId, callback);
    }
    @Test
    public void testRecupererRecettesSimilaires_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = -1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                // Le test échoue s'il y a une réponse réussie
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.recupererRecettesSimilaires(userId, recetteId, callback);
    }

    //ajouterRecetteAuFavoris Methode
    @Test
    public void testAjouterRecetteAuFavoris_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = 1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recette ajoutée aux favoris avec succès", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.ajouterRecetteAuFavoris(userId, recetteId, callback);
    }

    @Test
    public void testAjouterRecetteAuFavoris_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = -1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };
        backendManager.ajouterRecetteAuFavoris(userId, recetteId, callback);
    }

    //supprimerRecetteDeFavoris Methode
    @Test
    public void testSupprimerRecetteDeFavoris_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = 1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recette supprimée de favoris avec succès", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.supprimerRecetteDeFavoris(userId, recetteId, callback);
    }
    @Test
    public void testSupprimerRecetteDeFavoris_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long userId = -1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.supprimerRecetteDeFavoris(userId, recetteId, callback);
    }

    //ajouterRecetteAuStock Methode
    @Test
    public void testAjouterRecetteAuStock_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = 1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Recette ajouté au stock avec succès", response.getString("message"));
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.ajouterRecetteAuStock(stockId, recetteId, callback);
    }
    @Test
    public void testAjouterRecetteAuStock_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        long stockId = -1L;
        long recetteId = 38L;

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.ajouterRecetteAuStock(stockId, recetteId, callback);
    }

    //recupererIngredients Methode
    @Test
    public void testRecupererIngredients_Success() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                assertTrue(response.has("message"));
                assertEquals("Ingredients récupérés par succès",response.getString("message"));
                assertTrue(response.has("ingredients"));
                assertNotNull(response.getJSONArray("ingredients"));
                assertEquals(100, response.getJSONArray("ingredients").length());
            }

            @Override
            public void onError(Exception error) {
                fail("Error callback should not be invoked");
            }
        };

        backendManager.recupererIngredients(callback);
    }
    @Test
    public void testRecupererIngredients_Error() {
        Context context = ApplicationProvider.getApplicationContext();
        BackendManager backendManager = new BackendManager(context);

        BackendManager.BackendResponseCallback callback = new BackendManager.BackendResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                fail("onSuccess callback should not be invoked");
            }

            @Override
            public void onError(Exception error) {
                assertNotNull(error);
            }
        };

        backendManager.recupererIngredients(callback);
    }

}

