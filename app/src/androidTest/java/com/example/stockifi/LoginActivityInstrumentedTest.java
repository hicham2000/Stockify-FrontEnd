package com.example.stockifi;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityInstrumentedTest {

    @Rule
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        // Initialize ActivityScenario for the LoginActivity
        ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void testLoginButton() {
        // Perform actions to fill the login form
        // ...

        // Perform click on the login button
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click());

        // Check if the ProfilActivity is launched after successful login
        Espresso.onView(withId(R.layout.activity_profil)).check(matches(isDisplayed()));
    }

    @Test
    public void testForgottenPasswordLink() {
        // Perform click on the forgotten password link
        Espresso.onView(withId(R.id.textViewForgottenPwd)).perform(ViewActions.click());

        // Check if the ResetPasswordActivity is launched
        Espresso.onView(withId(R.layout.activity_reset_password)).check(matches(isDisplayed()));
    }

    @Test
    public void testCreateAccountLink() {
        // Perform click on the create account link
        Espresso.onView(withId(R.id.textViewCreateCompte)).perform(ViewActions.click());

        // Check if the RegisterActivity is launched
        Espresso.onView(withId(R.layout.activity_register)).check(matches(isDisplayed()));
    }

    // Add more tests for other functionalities in the LoginActivity
    // ...

}
