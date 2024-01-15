package com.example.stockifi.Liste_Course;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.stockifi.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ModifierProduitTest {

    @Before
    public void setup() {
        // Lancer l'activité avant chaque test
        ActivityScenario.launch(new Intent());
    }

    @Test
    public void testSauvegarderButton() {
        // Supposons que l'activité ListeDeCourse est déjà ouverte

        // Effectuer un clic sur l'élément à la position 0 de RecyclerView
        Espresso.onView(withId(R.id.myListViewCourse))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Vérifier que l'activité ModifierProduit est ouverte
        Espresso.onView(withId(R.layout.activity_modifier_produit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Effectuer des actions sur les vues de l'activité ModifierProduit
        Espresso.onView(withId(R.id.editTexte_modifProduit)).perform(ViewActions.replaceText("NouveauNom"));
        Espresso.onView(withId(R.id.quant_ajoutModif)).perform(ViewActions.replaceText("42"));

        // Faire défiler le Spinner à la position souhaitée
        Espresso.onView(withId(R.id.spinner_poid_ajoutModif1)).perform(ViewActions.click());
        Espresso.onData(RecyclerViewMatchers.atPosition(1)).perform(ViewActions.click());

        // Cliquer sur le bouton "Sauvegarder"
        Espresso.onView(withId(R.id.sauvegarder_prod_modif)).perform(ViewActions.click());

        // Vérifier que l'activité ListeDeCourse est relancée après le clic sur le bouton "Sauvegarder"
        Espresso.onView(withId(R.layout.activity_liste_de_course)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // Ajouter d'autres tests au besoin
}
