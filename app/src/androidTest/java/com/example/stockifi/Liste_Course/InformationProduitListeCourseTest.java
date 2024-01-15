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
public class InformationProduitListeCourseTest {

    @Rule
    public ActivityScenarioRule<information_produit_listeCourse> activityRule =
            new ActivityScenarioRule<>(information_produit_listeCourse.class);

    @Before
    public void setUp() {
        // You can perform any setup here before each test
    }

    @Test
    public void testInformationProduitListeCourse() {
        // Intent with necessary extras
        Intent intent = new Intent();
        intent.putExtra("id", 1);
        intent.putExtra("intitule", "SampleProduct");
        intent.putExtra("quantite", 10.0);
        intent.putExtra("mesure", "Kg");

        // Add extras to the intent
        activityRule.getScenario().onActivity(activity -> activity.setIntent(intent));

        // Replace these with the actual IDs of your views
        Espresso.onView(ViewMatchers.withId(R.id.editTexte_modifProduitInf))
                .check(ViewAssertions.matches(ViewMatchers.withText("SampleProduct")));

        Espresso.onView(ViewMatchers.withId(R.id.quant_ajoutModifInf))
                .check(ViewAssertions.matches(ViewMatchers.withText("10.0")));

        Espresso.onView(ViewMatchers.withId(R.id.spinner_poid_ajoutModif1Inf))
                .check(ViewAssertions.matches(ViewMatchers.withText("Kg")));
    }
}
