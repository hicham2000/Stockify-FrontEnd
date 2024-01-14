package com.example.stockifi.recettes;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class GetImageFromUrlTest {

    private ImageView mockImageView;
    private ProgressBar mockProgressBar;
    private GetImageFromUrl getImageFromUrl;
    private String imageUrl;

    @Before
    public void setUp() {
        mockImageView = new ImageView(ApplicationProvider.getApplicationContext());
        mockProgressBar = new ProgressBar(ApplicationProvider.getApplicationContext());
        getImageFromUrl = new GetImageFromUrl(mockImageView, mockProgressBar);
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6fapUw8ig1enEE756lyWfFgQigZw_Q_XBixn9t39R17c-_mmpLD1yALZNyw&s";
    }

    @Test
    public void doInBackground_withInternetConnection_shouldFetchImage() {
        Bitmap result = getImageFromUrl.doInBackground(imageUrl);

        assertNotNull(result);
    }

}

