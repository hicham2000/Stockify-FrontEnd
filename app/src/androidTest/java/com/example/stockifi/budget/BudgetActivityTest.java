package com.example.stockifi.budget;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.stockifi.R;

import org.junit.Rule;
import org.junit.Test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.widget.TextView;



public class BudgetActivityTest {

    @Rule
    public ActivityScenarioRule<budgetActivity> activityScenarioRule =
            new ActivityScenarioRule<>(budgetActivity.class);

    @Test
    public void checkInitialViewsDisplayed() {
        // Check if the initial views are displayed when the activity starts
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_budget)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.montant_total)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.pourcentage_consomme)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.pourcentage_gaspille)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // Add more assertions for other views if needed
    }

    @Test
    public void checkNavigationToCorbeille() {
        // Click on the poubelle icon in the toolbar and check if it navigates to the corbeille activity
        Espresso.onView(ViewMatchers.withId(R.id.poubelle)).perform(ViewActions.click());
        // Add more assertions for the corbeille activity if needed
    }


    @Test
    public void checkBudgetValuesUpdate() {
        // Mock the response for budget values and check if the TextViews are updated accordingly
        // Replace these mock values with the expected values after parsing the API response
        String mockApiResponse = "[1600.0, 800.0, 800.0, 50.0, 50.0]"; // Adjusted mock values

        // Use Espresso Intents to intercept the HTTP request (if needed) and provide a mock response
        // ...

        // Re-launch the activity to see if it updates the TextViews
        ActivityScenario.launch(budgetActivity.class);

        // Check if the TextViews display the expected values
        checkTextViewWithFormattedText(R.id.montant_total, "1600 DH");
        checkTextViewWithFormattedText(R.id.montant_consomme, "1600 DH");
        checkTextViewWithFormattedText(R.id.montant_gaspille, "400 DH");
        checkTextViewWithFormattedText(R.id.pourcentage_consomme, "80%");
        checkTextViewWithFormattedText(R.id.pourcentage_gaspille, "20%");
    }

    private static void checkTextViewWithFormattedText(int viewId, String expectedText) {
        Espresso.onView(ViewMatchers.withId(viewId)).check(ViewAssertions.matches(withFormattedText(expectedText)));
    }

    private static Matcher<View> withFormattedText(final String expectedText) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextView)) {
                    return false;
                }

                String actualText = ((TextView) item).getText().toString();

                // Extract numeric parts of the texts with optional decimal points
                String expectedNumeric = expectedText.replaceAll("[^\\d.]", "");
                String actualNumeric = actualText.replaceAll("[^\\d.]", "");

                // Log information for debugging
                System.out.println("Expected: " + expectedText);
                System.out.println("Actual: " + actualText);
                System.out.println("Expected Numeric Part: " + expectedNumeric);
                System.out.println("Actual Numeric Part: " + actualNumeric);
                System.out.println("Expected Numeric Type: " + expectedNumeric.getClass().getName());
                System.out.println("Actual Numeric Type: " + actualNumeric.getClass().getName());

                return expectedNumeric.equals(actualNumeric);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with formatted text: " + expectedText);
            }
        };
    }
}
