package com.example.stockifi;

import android.content.Context;
import android.widget.Button;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.stockifi.R;

import androidx.test.core.app.ApplicationProvider;

import com.example.stockifi.corbeille.corbeille;
import com.example.stockifi.corbeille.corbeilleAdapter;
import com.example.stockifi.corbeille.corbeillerepasadapter;
import com.example.stockifi.corbeille.objet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import androidx.test.platform.app.InstrumentationRegistry;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class CorbeilleActivityTest {

    @Test
    public void testCorbeilleAdapterCreation() {
        Context context = ApplicationProvider.getApplicationContext();
        corbeilleAdapter adapter = new corbeilleAdapter(context, new ArrayList<>());

        assertNotNull(adapter);
    }

    @Test
    public void testCorbeilleRepasAdapterCreation() {
        Context context = ApplicationProvider.getApplicationContext();
        corbeillerepasadapter repasAdapter = new corbeillerepasadapter(context, new ArrayList<>());

        assertNotNull(repasAdapter);
    }

    @Test
    public void testObjetCreation() {
        objet obj = new objet(1, "Test", true, 2, 0);

        assertNotNull(obj);
        assertEquals(1, obj.getId());
        assertEquals("Test", obj.getIntitule());
        assertEquals(true, obj.getCheck());
        assertEquals(2, obj.getStockId());
        assertEquals(0, obj.getGaspille());
    }
    @Rule
    public ActivityScenarioRule<corbeille> activityScenarioRule =
            new ActivityScenarioRule<>(corbeille.class);
    @Test
    public void testProduitsButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.produits)).perform(ViewActions.click());

    }

    @Test
    public void testRepasButtonClick() {
        Espresso.onView(ViewMatchers.withId(R.id.repas)).perform(ViewActions.click());

    }
}
