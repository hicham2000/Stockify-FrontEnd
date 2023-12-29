package com.example.stockifi.recettes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
    ImageView imageView;
    Bitmap bitmap;
    public GetImageFromUrl(ImageView img){
        this.imageView = img;
        System.out.println("this.imageView = " + this.imageView);
    }
    @Override
    protected Bitmap doInBackground(String... url) {
        String stringUrl = url[0];
        bitmap = null;
        InputStream inputStream;
        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
