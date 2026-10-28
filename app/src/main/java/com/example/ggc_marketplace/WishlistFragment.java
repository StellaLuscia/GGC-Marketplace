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
import android.widget.Button;

import java.util.ArrayList;

public class WishlistFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> wishlistArrayList = new ArrayList<Model>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        recyclerView = view.findViewById(R.id.recycleView);

        setAdapter();

        return view;
    }

    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(wishlistArrayList, listener);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ListingDetailsActivity.class);
                intent.putExtra("name", wishlistArrayList.get(position).getName());
                intent.putExtra("price", wishlistArrayList.get(position).getPrice());
                intent.putExtra("image", wishlistArrayList.get(position).getImage());
                startActivity(intent);
            }
        };
    }
}
