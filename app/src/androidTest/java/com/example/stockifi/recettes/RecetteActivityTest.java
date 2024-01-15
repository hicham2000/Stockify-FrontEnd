package com.example.stockifi.recettes;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
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
public class RecetteActivityTest {

    private ActivityScenario<RecetteActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(RecetteActivity.class);
    }

    @Test
    public void testUIComponents() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_image_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.recette_nom_text_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.recette_text_view_nombre_ingredients_manquants))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    @Test
    public void testPortionButtonClickMinus() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_Button_portion_minus))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_text_view_portion))
                .check(ViewAssertions.matches(ViewMatchers.withText("1")));
    }

    @Test
    public void testPortionButtonClickPlus() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_Button_portion_plus))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_text_view_portion))
                .check(ViewAssertions.matches(ViewMatchers.withText("2")));
    }
    @Test
    public void testIngredientsDropdownButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_ingredients_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_ingredients_listView))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        Espresso.onView(ViewMatchers.withId(R.id.recette_ingredients_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_ingredients_listView))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
    @Test
    public void testInstructionsDePreparationDropdownButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_instructions_de_preparation_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_instruction_de_preparation_listView))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        Espresso.onView(ViewMatchers.withId(R.id.recette_instructions_de_preparation_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_instruction_de_preparation_listView))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
    @Test
    public void testValeursNutritionnellesButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recette_valeurs_nutritionnelles_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_valeurs_nutritionnelles_list))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        Espresso.onView(ViewMatchers.withId(R.id.recette_valeurs_nutritionnelles_dropdownButton))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.recette_valeurs_nutritionnelles_list))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
    @After
    public void tearDown() {
        activityScenario.close();
    }

}