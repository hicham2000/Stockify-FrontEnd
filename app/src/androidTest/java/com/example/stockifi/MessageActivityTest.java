package com.example.stockifi;
import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.stockifi.MessageActivity;
import com.example.stockifi.sqlite.DatabaseHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MessageActivityTest {

    @Rule
    public ActivityScenarioRule<MessageActivity> activityScenarioRule = new ActivityScenarioRule<>(MessageActivity.class);

    private DatabaseHelper dbHelper;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new DatabaseHelper(context);
    }

    @Test
    public void clearNotificationsTest() {
        dbHelper.addNotification("Test Notification 1", "This is a test notification 1");
        dbHelper.addNotification("Test Notification 2", "This is a test notification 2");

        onView(withId(R.id.trash)).perform(ViewActions.click());

        onView(ViewMatchers.withText("Oui")).perform(ViewActions.click());

        onView(withId(R.id.notificationListView)).check(ViewAssertions.matches(RecyclerViewItemCountAssertion.withItemCount(0)));
    }
}