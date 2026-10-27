package com.example.ggc_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        //Default values assigned
        String name = "Name not set!";
        String desc = "Price not set!";
        int image = 0;

        //Grabs information from each individual listing to pass to the details activity
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            name = extras.getString("name");
            desc = extras.getString("price");
            image = getIntent().getIntExtra("image", 0);
        }

        //Attaches above information to details activity's layout to view listing's image, name, and price
        txt.setText(name);
        txt2.setText(desc);
        img.setImageResource(image);

    }
    //Sets up focus to later pass image, name, and price information to listing details activity's layout
    private void setUpViews() {
        img = (ImageView) findViewById(R.id.listing_img);
        txt = (TextView) findViewById(R.id.listing_name);
        txt2 = (TextView) findViewById(R.id.listing_price);
    }
}
