package com.example.stockifi.recettes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = GetImageFromUrl.class.getSimpleName();
    private ImageView imageView;
    private Bitmap bitmap;

    public GetImageFromUrl(ImageView img) {
        this.imageView = img;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String stringUrl = url[0];
        bitmap = null;

        try {
            URL imageUrl = new URL(stringUrl);

            if (isInternetConnected()) {
                HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } else {
                Log.e(TAG, "No internet connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            // Handle the case where the image could not be fetched
            Log.e(TAG, "Error fetching image");
        }
    }

    private boolean isInternetConnected() {

        return true;
    }
}
