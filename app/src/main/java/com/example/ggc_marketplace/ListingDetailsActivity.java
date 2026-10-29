package com.example.ggc_marketplace;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListingDetailsActivity extends AppCompatActivity {

    private ImageView img;
    private TextView txt, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Back button to return to main activity & fragment
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setUpViews();

        String price, name, cat;
        int image;

        //Grabs information from each individual listing to later pass to the ListingDetailsActivity layout and Wishlist and Cart fragment layouts
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            price = extras.getString("price");
            name = extras.getString("name");
            cat = extras.getString("type");
            image = getIntent().getIntExtra("image", 0);
        } else {
            price = "Price not set!";
            name = "Name not set!";
            cat = "Category not set!";
            image = 0;
        }

        //Attaches above information to ListingDetailsActivity's layout to view listing's image, name, and price
        txt.setText(name);
        txt2.setText(price);
        img.setImageResource(image);

        //Setting up add to wishlist button functionality - uses arrayList from WishlistFragment & passes the same "price," "name," "image," and "category" values assigned above
        Button wishlistBtn = findViewById(R.id.wishlistBtn);
        wishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistFragment.wishlistArrayList.add(new Model(price, name, image, cat));
                Toast.makeText(getApplicationContext(), "Added to Wishlist!", Toast.LENGTH_SHORT).show();
            }
        });

        //Setting up add to cart button functionality - uses arrayList from CartFragment & passes the same "price," "name," "image," and "category" values assigned above
        Button cartBtn = findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment.cartArrayList.add(new Model(price, name, image, cat));
                Toast.makeText(getApplicationContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Sets up focus to later pass image, name, and price information to ListingDetailsActivity's layout
    private void setUpViews() {
        img = (ImageView) findViewById(R.id.listing_img);
        txt = (TextView) findViewById(R.id.listing_name);
        txt2 = (TextView) findViewById(R.id.listing_price);
    }
}
