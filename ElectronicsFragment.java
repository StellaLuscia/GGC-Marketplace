package com.example.ggc_marketplace;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ElectronicsFragment extends Fragment {
    //
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> electronicsList = new ArrayList<Model>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_electronics, container, false);

        //Back button to return to home/all listings page
        Button backBtn = view.findViewById(R.id.backBtn4);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RecyclerHomeFragment());
                fr.commit();
            }
        });

        //Sets up recycler view from fragment's layout
        recyclerView = view.findViewById(R.id.recycleView5);

        initializeList();

        setAdapter();

        return view;
    }

    //Sets up recycler view as grid w/ 2 columns & passes arraylist along with onClickListener to view individual listing info when listing is clicked
    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(electronicsList, listener);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    //Used to view individual listings, displaying their name, price, and image
    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ListingDetailsActivity.class);
                intent.putExtra("name", electronicsList.get(position).getName());
                intent.putExtra("price", electronicsList.get(position).getPrice());
                intent.putExtra("image", electronicsList.get(position).getImage());
                startActivity(intent);
            }
        };
    }
    //Sets up electronicsList's objects/electronic listings
    private void initializeList() {
        electronicsList.clear();

        Model macbook = new Model("$312", "MacBook Air 13-in", R.drawable.macbook, "Electronics");
        electronicsList.add(macbook);
        Model pink_smartWatch = new Model("$115", "Pink Smartwatch", R.drawable.pink_smartwatch, "Electronics");
        electronicsList.add(pink_smartWatch);
        Model calculator = new Model("$11", "Black Calculator", R.drawable.calculator, "Electronics");
        electronicsList.add(calculator);
        Model black_headphones = new Model("$56", "Black BT Headphones", R.drawable.black_headphones, "Electronics");
        electronicsList.add(black_headphones);
        Model earbuds = new Model("$44", "BT Earbuds w/ Case", R.drawable.earbuds, "Electronics");
        electronicsList.add(earbuds);
        Model ipad = new Model("$127", "iPad 9th Gen", R.drawable.ipad, "Electronics");
        electronicsList.add(ipad);
        Model wacom = new Model("$78", "Wacom Tablet", R.drawable.wacom, "Electronics");
        electronicsList.add(wacom);
        Model power_bank = new Model("$31", "Power Bank 20k-mAh", R.drawable.power_bank, "Electronics");
        electronicsList.add(power_bank);

    }
}