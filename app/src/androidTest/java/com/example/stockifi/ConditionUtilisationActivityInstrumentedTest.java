package com.example.stockifi;

import android.net.Uri;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ConditionUtilisationActivityInstrumentedTest {

    @Before
    public void setUp() {
        // Initialize Intents before each test
        Intents.init();
        // Launch ActivityScenario for the ConditionUtilisationActivity
        ActivityScenario.launch(ConditionUtilisationActivity.class);
    }

    @After
    public void tearDown() {
        // Release Intents after each test
        Intents.release();
    }

    @Test
    public void testBackButton() {
        // Perform click on the back button
        Espresso.onView(withId(R.id.toolbar_back_button_condi)).perform(ViewActions.click());

        // Check if the onBackPressed method is called and the activity is finished
        intended(allOf(hasAction("android.intent.action.MAIN"), hasData((Uri) null)));

        // Add additional assertions if needed
    }
}
