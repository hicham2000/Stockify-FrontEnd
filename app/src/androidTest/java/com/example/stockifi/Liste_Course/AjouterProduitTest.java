package com.example.stockifi.Liste_Course;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.stockifi.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AjouterProduitTest {

    @Rule
    public ActivityScenarioRule<AjouterProduit> activityRule =
            new ActivityScenarioRule<>(AjouterProduit.class);

    @Before
    public void setUp() {
        // You can perform any setup here before each test
    }

    @Test
    public void testAjouterProduit() {
        // Intent with necessary extras (if needed)
        Intent intent = new Intent();
        // Add extras if needed
        activityRule.getScenario().onActivity(activity -> activity.setIntent(intent));

        // Replace these with the actual IDs of your views
        Espresso.onView(ViewMatchers.withId(R.id.editTexte_nomProduit))
                .perform(ViewActions.typeText("SampleProduct"));

        Espresso.onView(ViewMatchers.withId(R.id.quant_ajouterProduit))
                .perform(ViewActions.typeText("10"));

        // Select an item in the spinner (replace with your actual spinner ID)
        Espresso.onView(ViewMatchers.withId(R.id.spinner_poid_ajoutProduit))
                .perform(ViewActions.click());

        // Replace with the actual string value you want to select in the spinner
       // Espresso.onData(ViewMatchers.withAlpha("Kg"))
         //       .perform(ViewActions.click());

        // Click the button to add the product
        Espresso.onView(ViewMatchers.withId(R.id.ajoutbasedonnes))
                .perform(ViewActions.click());

        // You can add additional assertions based on the expected behavior
        // For example, you can check if the Toast message is displayed
        Espresso.onView(ViewMatchers.withText("Produit ajouté avec succès"))
                .inRoot(new ToastMatcher())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
