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

public class ClothingFragment extends Fragment {
    //
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> clothingList = new ArrayList<Model>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_clothing, container, false);

        //Back button to return to home/all listings page
        Button backBtn = view.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RecyclerHomeFragment());
                fr.commit();
            }
        });

        //Sets up recycler view from fragment's layout
        recyclerView = view.findViewById(R.id.recycleView2);

        initializeList();

        setAdapter();

        return view;
    }

    //Sets up recycler view as grid w/ 2 columns  & passes arraylist along with onClickListener to view individual listing info when listing is clicked
    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(clothingList, listener);
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
                intent.putExtra("name", clothingList.get(position).getName());
                intent.putExtra("price", clothingList.get(position).getPrice());
                intent.putExtra("image", clothingList.get(position).getImage());
                startActivity(intent);
            }
        };
    }

    //Sets up clothingList's objects/clothing listings
    private void initializeList() {
        clothingList.clear();

        Model patterned_blouse = new Model("$25", "Patterned Blouse", R.drawable.patterned_blouse, "Clothing");
        clothingList.add(patterned_blouse);
        Model corduroy_buttonUp = new Model("$32", "Corduroy Button Up", R.drawable.corduroy_button_up, "Clothing");
        clothingList.add(corduroy_buttonUp);
        Model scarf = new Model("$19", "Multicolor Knit Scarf", R.drawable.scarf, "Clothing");
        clothingList.add(scarf);
        Model khaki_pants = new Model("$38", "Khaki Pants", R.drawable.khaki_pants, "Clothing");
        clothingList.add(khaki_pants);
        Model pink_blouse = new Model("$35", "Dusty Pink Blouse", R.drawable.pink_blouse, "Clothing");
        clothingList.add(pink_blouse);
        Model plaid_jacket = new Model("$40", "Multicolor Plaid Jacket", R.drawable.plaid_jacket, "Clothing");
        clothingList.add(plaid_jacket);
        Model wool_coat = new Model("$64", "Beige Wool Coat", R.drawable.wool_coat, "Clothing");
        clothingList.add(wool_coat);
        Model leather_jacket = new Model("$56", "Brown Leather Jacket", R.drawable.leather_jacket, "Clothing");
        clothingList.add(leather_jacket);
    }
}