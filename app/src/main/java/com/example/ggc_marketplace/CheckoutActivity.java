package com.example.ggc_marketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private TextView txtTotal;
    private TextView txtOrderSummary;
    private Button confirmBtn, cancelBtn;

    private double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        setUpViews();

        calculateTotalPrice();

        displayOrderSummary();

        txtTotal.setText(String.format(
                Locale.US, "Total Price: $%.2f", totalPrice
        ));

        setOnClickListeners();
    }

    // Connects Java variables to XML views
    private void setUpViews() {
        txtTotal = findViewById(R.id.txtTotal);
        txtOrderSummary = findViewById(R.id.txtOrderSummary);
        confirmBtn = findViewById(R.id.confirmBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
    }

    // Calculates total price of cart items
    private void calculateTotalPrice() {

        totalPrice = 0;

        for (Model listing : CartFragment.cartArrayList) {

            try {
                String price = listing.getPrice();

                if (price != null) {
                    totalPrice += Double.parseDouble(
                            price.replaceAll("[^0-9.]", "")
                    );
                }

            } catch (NumberFormatException e) {
                // Ignore invalid prices
            }
        }
    }

    // Displays all items in the order
    private void displayOrderSummary() {

        StringBuilder summary = new StringBuilder();

        for (Model listing : CartFragment.cartArrayList) {

            summary.append(listing.getName())
                    .append(" - ")
                    .append(listing.getPrice())
                    .append("\n\n");
        }

        if (summary.length() == 0) {
            summary.append("No items in cart.");
        }

        txtOrderSummary.setText(summary.toString());
    }

    // Sets up button functionality
    private void setOnClickListeners() {

        // Confirm order button
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CartFragment.cartArrayList.isEmpty()) {

                    Toast.makeText(
                            getApplicationContext(),
                            "Your cart is empty!",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    new AlertDialog.Builder(CheckoutActivity.this)
                            .setTitle("Order Confirmed!")
                            .setMessage(
                                    "Thank you for shopping with GGC Marketplace!\n\n"
                                            + String.format(Locale.US,
                                            "Your total is $%.2f", totalPrice)
                            )
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> {

                                CartFragment.cartArrayList.clear();

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Order placed successfully!",
                                        Toast.LENGTH_SHORT
                                ).show();

                                finish();
                            })
                            .show();
                }
            }
        });

        // Cancel checkout button
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}