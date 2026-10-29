package com.example.ggc_marketplace;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.wallet.PaymentsClient;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    //Fragment follows similar setup to WishlistFragment
    View view;
    private TextView priceTotalView;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> cartArrayList = new ArrayList<Model>();
    //cartArrayList objects previously initialized in ListingDetailsActivity

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        priceTotalView = (TextView) view.findViewById(R.id.priceTotal);

        setAdapter();

        calculateTotalPrice(cartArrayList);

        return view;
    }

    //Sets up recycler view to display cart listings
    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(cartArrayList, listener);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    //Used to view listing details, just like in RecyclerHomeFragment
    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ListingDetailsActivity.class);
                intent.putExtra("name", cartArrayList.get(position).getName());
                intent.putExtra("price", cartArrayList.get(position).getPrice());
                intent.putExtra("image", cartArrayList.get(position).getImage());
                startActivity(intent);
            }
        };
    }

    private void calculateTotalPrice(ArrayList<Model> list) {
        String priceString;
        String displayTxt;
        int price = 0;
        int totalPrice = 0;

        if(list != null) {
            for(Model listing : list) {
                priceString = listing.getPrice();
                try {
                    price = Integer.parseInt(priceString.replaceAll("[^0-9]", ""));
                }
                catch(NumberFormatException e) {
                    price = 0;
                }
                totalPrice += price;
            }
            displayTxt = String.valueOf(totalPrice);
            priceTotalView.setText(String.format("Total Price: $%s", displayTxt));
        }
    }
}
