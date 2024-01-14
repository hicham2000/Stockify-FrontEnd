package com.example.stockifi.recettes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = GetImageFromUrl.class.getSimpleName();
    private ImageView imageView;
    private ProgressBar loadingProgressBar;
    private Bitmap bitmap;

    public GetImageFromUrl(ImageView img, ProgressBar progressBar) {
        this.imageView = img;
        this.loadingProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingProgressBar.setVisibility(View.VISIBLE);
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
            loadingProgressBar.setVisibility(View.GONE);
        } else {
            Log.e(TAG, "Error fetching image");
        }
    }

    private boolean isInternetConnected() {
        Context context = imageView.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
