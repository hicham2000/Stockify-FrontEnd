package com.example.stockifi;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.stockifi.RegisterActivity;
import com.example.stockifi.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

public class RegisterActivityTest {

    @Rule
    public IntentsTestRule<RegisterActivity> activityRule = new IntentsTestRule<>(RegisterActivity.class);

    @Before
    public void setUp() {
        // Stub the image picker intent
        Intent resultData = new Intent();
        Uri uri = Uri.parse("content://media/external/images/media/1");
        resultData.setData(uri);
        intending(allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)))
                .respondWith(new Instrumentation.ActivityResult(0, resultData));
    }

    @Test
    public void testImageUpload() {
        // Perform click on the upload image button
        Espresso.onView(withId(R.id.camera_icon)).perform(ViewActions.click());

        // Check if the image is displayed after selection
        Espresso.onView(withId(R.id.camera_icon)).check(matches(isDisplayed()));
    }

    @Test
    public void testInscriptionButton() {
        // Perform actions to fill the registration form
        // ...

        // Perform click on the inscription button
        Espresso.onView(withId(R.id.registerButton)).perform(ViewActions.click());

        // Check if the LoginActivity is launched after successful registration
        Espresso.onView(withText("LoginActivity")).check(matches(isDisplayed()));
    }

    // Add more tests for other functionalities in the RegisterActivity
    // ...

}
