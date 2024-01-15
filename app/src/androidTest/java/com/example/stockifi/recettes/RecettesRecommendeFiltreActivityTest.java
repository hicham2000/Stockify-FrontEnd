package com.example.stockifi.recettes;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.content.ComponentName;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.stockifi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecettesRecommendeFiltreActivityTest {

    private ActivityScenario<RecettesRecommendeFiltreActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(RecettesRecommendeFiltreActivity.class);
    }

    @After
    public void tearDown() {
        activityScenario.close();
    }


    @Test
    public void testAddProduitButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_Button_add_produit))
                .perform(ViewActions.click());

        intended(hasComponent(SelectionnerProduitActivity.class.getName()));
    }

    @Test
    public void testAppliquerButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_recommende_filtre_appliquer_button))
                .perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasComponent(new ComponentName(getTargetContext(), RecettesRecommendeActivity.class)));
    }

    @Test
    public void testAnnulerButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_recommende_filtre_annuler_button))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.regime_speciaux_recycler_view))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));

        Espresso.onView(ViewMatchers.withId(R.id.produits_selectionne))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));

        Espresso.onView(ViewMatchers.withId(R.id.temps_de_preparation_seekBar))
                .check(ViewAssertions.matches(ViewMatchers.withText("120 min")));
    }
}