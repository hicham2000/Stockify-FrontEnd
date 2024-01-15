package com.example.stockifi;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityInstrumentedTest {

    @Rule
    public IntentsTestRule<RegisterActivity> activityRule = new IntentsTestRule<>(RegisterActivity.class);

    @Before
    public void setUp() {
        Intent resultData = new Intent();
        Uri uri = Uri.parse("content://media/external/images/media/1");
        resultData.setData(uri);
        intending(allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)))
                .respondWith(new Instrumentation.ActivityResult(0, resultData));
    }

    @Test
    public void testImageUpload() {
        onView(withId(R.id.camera_icon)).perform(ViewActions.click());
        onView(withId(R.id.camera_icon)).check(matches(isDisplayed()));
    }

    @Test
    public void testInscriptionButton() {
        onView(withId(R.id.registerButton)).perform(ViewActions.click());
        onView(withText("LoginActivity")).check(matches(isDisplayed()));
    }

    @Test
    public void testSignupWithValidInput() {
        onView(withId(R.id.nom)).perform(ViewActions.typeText("NomTest"));
        onView(withId(R.id.prenom)).perform(ViewActions.typeText("PrenomTest"));
        onView(withId(R.id.email)).perform(ViewActions.typeText("test@example.com"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("TestPassword123!"));
        onView(withId(R.id.confirm_password)).perform(ViewActions.typeText("TestPassword123!"));
        onView(withId(R.id.regime_option)).perform(ViewActions.click());
        onView(withId(R.id.checkBox)).perform(ViewActions.click());

        onView(withId(R.id.registerButton)).perform(ViewActions.click());

        onView(withText("LoginActivity")).check(matches(isDisplayed()));
    }

    @Test
    public void testSignupWithInvalidInput() {
        onView(withId(R.id.nom)).perform(ViewActions.typeText("NomTest"));
        onView(withId(R.id.prenom)).perform(ViewActions.typeText("PrenomTest"));
        onView(withId(R.id.email)).perform(ViewActions.typeText("test@example.com"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("TestPassword123!"));
        onView(withId(R.id.confirm_password)).perform(ViewActions.typeText("TestPassword123!"));

        onView(withId(R.id.registerButton)).perform(ViewActions.click());

        onView(withText("Accepter les conditions d'utilisation"))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));


    }
}
