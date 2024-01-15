package com.example.stockifi.Liste_Course;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.stockifi.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ListeDeCourseTest {

    @Rule
    public ActivityScenarioRule<ListeDeCourse> activityRule = new ActivityScenarioRule<>(ListeDeCourse.class);

    @Test
    public void testSearchFunctionality() {
        // Type text in the search view
        Espresso.onView(ViewMatchers.withId(R.id.search_course)).perform(ViewActions.typeText("example"), ViewActions.pressImeActionButton());

        // Add assertions based on the expected behavior
        // For example, check if the RecyclerView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.myListViewCourse)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // You can also check if a specific item is displayed in the RecyclerView
        Espresso.onView(ViewMatchers.withId(R.id.myListViewCourse))
                .perform(RecyclerViewActions.scrollToPosition(0)) // Scroll to the first position
                .check(ViewAssertions.matches(RecyclerViewMatchers.atPosition(0)));
    }

    @Test
    public void testButtonClick() {
        // Click the "Ajouter Produit" button
        Espresso.onView(ViewMatchers.withId(R.id.ajouter_produit)).perform(ViewActions.click());

        // Add assertions based on the expected behavior
        // For example, check if a specific view is displayed
        Espresso.onView(ViewMatchers.withId(R.id.myListViewCourse)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // Add more test methods based on the functionalities you want to test
    // ...

}
