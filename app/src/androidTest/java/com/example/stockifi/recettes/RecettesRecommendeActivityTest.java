package com.example.stockifi.recettes;

import static androidx.test.InstrumentationRegistry.getTargetContext;

import android.content.ComponentName;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.stockifi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecettesRecommendeActivityTest {

    @Rule
    public ActivityScenarioRule<RecettesRecommendeActivity> activityRule =
            new ActivityScenarioRule<>(RecettesRecommendeActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testFavorisButtonClickListener() {
        Espresso.onView(ViewMatchers.withId(R.id.btnFavoris))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.btnFavoris))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(R.drawable.ellipse_menu)));

        Espresso.onView(ViewMatchers.withId(R.id.btnTous))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(android.R.color.transparent)));

        Espresso.onView(ViewMatchers.withId(R.id.btnFiltre))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(android.R.color.transparent)));

        Intents.intended(IntentMatchers.anyIntent(), Intents.times(1));
    }

    @Test
    public void testBtnTousClickListener() {
        Espresso.onView(ViewMatchers.withId(R.id.btnTous))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.grid_recettes_recommende))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(6)));

        Espresso.onView(ViewMatchers.withId(R.id.btnTous))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(R.drawable.ellipse_menu)));

        Espresso.onView(ViewMatchers.withId(R.id.btnFavoris))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(android.R.color.transparent)));

        Espresso.onView(ViewMatchers.withId(R.id.btnFiltre))
                .check(ViewAssertions.matches(ViewMatchers.hasBackground(android.R.color.transparent)));
    }

    @Test
    public void testBtnFiltreClickListener() {
        Espresso.onView(ViewMatchers.withId(R.id.btnFiltre))
                .perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasComponent(new ComponentName(getTargetContext(), RecettesRecommendeFiltreActivity.class)));
    }

}