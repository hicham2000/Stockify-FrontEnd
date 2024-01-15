package com.example.stockifi.Liste_Course;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class ListeCourseAdapterTest {

    @Rule
    public ActivityScenarioRule<TestActivity> activityRule = new ActivityScenarioRule<>(TestActivity.class);

    private ListeCourseAdapter adapter;

    @Before
    public void setUp() {
        // Add any setup code here if needed
    }

    @Test
    public void testListeCourseAdapter() {

        ArrayList<Produit> produits = new ArrayList<>();

        // Add some sample data (modify as needed)
        Produit produit1 = new Produit(1,"coca",22,"kg",true);
        Produit produit2 =new Produit(2,"patte",22,"kg",true);
        Produit produit3 = new Produit(3,"spaghitti",22,"kg",true);

        produits.add(produit1);
        produits.add(produit2);
        produits.add(produit3);
        // Add test data (modify as needed)

        // Add any additional setup needed for your adapter

        activityRule.getScenario().onActivity(activity -> {
            // Initialize the adapter with the test data
            adapter = new ListeCourseAdapter(activity, produits);

            // Create a ListView and set the adapter
            ListView listView = new ListView(activity);
            listView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            listView.setAdapter(adapter);

            // Launch the activity with the ListView
            activity.setContentView(listView);
        });

        // Now, you can perform UI interactions using Espresso
        // For example, check if the checkbox in the adapter is clickable
        Espresso.onView(ViewMatchers.withId(R.id.checkBox1)).perform(ViewActions.click());
    }

    // This is a simple activity class used for testing purposes
    public static class TestActivity extends androidx.fragment.app.FragmentActivity {
    }
}
