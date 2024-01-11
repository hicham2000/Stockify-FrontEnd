package com.example.stockifi;
import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Button;
import com.example.stockifi.cards.Card;
import android.view.LayoutInflater;
import android.view.View;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProduitCardActivity extends AppCompatActivity {

    private FrameLayout cardContainer;
    private List<Card> cardList;
    private int currentCardIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_card);
        cardContainer = findViewById(R.id.cardContainer);
        cardList = loadCardsFromJson();


        if (cardList != null && !cardList.isEmpty()) {
            showNextCard();

        }

    }

    private void showNextCard() {
        if (currentCardIndex < cardList.size()) {
            Card card = cardList.get(currentCardIndex);


            View cardView = LayoutInflater.from(this).inflate(R.layout.card_layout, cardContainer, false);

            ImageView imageView = cardView.findViewById(R.id.cardImageView);
            Button yesButton = cardView.findViewById(R.id.yesButton);
            Button noButton = cardView.findViewById(R.id.noButton);
            TextView titleCard = cardView.findViewById(R.id.cardtitle);
            String url = card.getImageUrl();
            Log.e("url",url);
            Glide.with(this)
                    .load(url)

                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            titleCard.setText(card.getTitle());
            yesButton.setOnClickListener(v -> {
                Intent intent = new Intent(ProduitCardActivity.this, Sprint2Activity.class);
                intent.putExtra("image_url",card.getImageUrl());
                intent.putExtra("title",card.getTitle());
                startActivity(intent);

            });


            noButton.setOnClickListener(v -> {
                cardContainer.removeView(cardView);
                currentCardIndex++;
                showNextCard();
            });

            cardContainer.addView(cardView);
        } else {

            startActivity(new Intent(ProduitCardActivity.this, Sprint2Activity.class));

        }
    }

    // load card from json function implementation :

    private List<Card> loadCardsFromJson() {
        List<Card> cardList = new ArrayList<>();
        try {

            InputStream inputStream = getResources().openRawResource(R.raw.products);
            int size = inputStream.available();
            if(size ==0 ){ Log.d("unsuccess","cads not loaded ");}
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCard = jsonArray.getJSONObject(i);

                String imageUrl = jsonCard.getString("url_image");
                String name = jsonCard.getString("title");

                Card card = new Card(imageUrl,name);

                cardList.add(card);
            }




        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error parsing JSON");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("unsuccess", "Error loading cards");
            return  null;
        }

        return  cardList;
    }


}