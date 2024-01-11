package com.example.stockifi.cards;
public class Card{

        private String imageUrl;
        private String title;


        public Card( String imageUrl,String title) {

            this.imageUrl = imageUrl;
            this.title = title;

        }



        public String getImageUrl() {
            return this.imageUrl;
        }
        public String getTitle() {
            return this.title;
        }

    }


