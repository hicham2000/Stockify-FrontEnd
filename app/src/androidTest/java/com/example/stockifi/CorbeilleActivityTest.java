package com.example.stockifi;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.stockifi.corbeille.corbeilleAdapter;
import com.example.stockifi.corbeille.corbeillerepasadapter;
import com.example.stockifi.corbeille.objet;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

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


}
