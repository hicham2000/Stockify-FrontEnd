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
    public IntentsTestRule<LoginActivity> activityRule = new IntentsTestRule<>(LoginActivity.class, false, false);

    @Before
    public void setUp() {
        ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void testLoginButton_Success() {
        Espresso.onView(withId(R.id.email_login)).perform(ViewActions.typeText("rifaywassim@gmail.com"));
        Espresso.onView(withId(R.id.password_login)).perform(ViewActions.typeText("123456@Wassim"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click());
        Espresso.onView(withId(R.layout.activity_profil)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginButton_Failure() {
        Espresso.onView(withId(R.id.email_login)).perform(ViewActions.typeText("example@gmail.com"));
        Espresso.onView(withId(R.id.password_login)).perform(ViewActions.typeText("123456@Wassim"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.button_login)).perform(ViewActions.click());
        //complete this parts
    }

    @Test
    public void testForgottenPasswordLink() {
        Espresso.onView(withId(R.id.textViewForgottenPwd)).perform(ViewActions.click());
        Espresso.onView(withId(R.layout.activity_reset_password)).check(matches(isDisplayed()));
    }

    @Test
    public void testCreateAccountLink() {
        Espresso.onView(withId(R.id.textViewCreateCompte)).perform(ViewActions.click());
        Espresso.onView(withId(R.layout.activity_register)).check(matches(isDisplayed()));
    }

}
