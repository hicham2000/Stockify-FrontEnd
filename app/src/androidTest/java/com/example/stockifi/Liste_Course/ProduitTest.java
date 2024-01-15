package com.example.stockifi.Liste_Course;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;
import com.example.stockifi.Liste_Course.ListeDeCourse;

import org.junit.Rule;
import org.junit.Test;

public class ProduitTest {

    @Rule
    public ActivityScenarioRule<ListeDeCourse> activityScenarioRule =
            new ActivityScenarioRule<>(ListeDeCourse.class);

    @Test
    public void testProduitDisplay() {
        Produit produit = new Produit(1, "Produit Test", 2.5, "kg", true);

        // Affichez votre activité
        Espresso.onView(ViewMatchers.withId(R.id.myListViewCourse))
                .check(new RecyclerViewItemAssertion<>(produit, R.id.myListViewCourse));
    }
}
