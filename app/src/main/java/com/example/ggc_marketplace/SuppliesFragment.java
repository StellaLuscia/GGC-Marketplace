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

public class SuppliesFragment extends Fragment {
    private RecyclerView recyclerView;

    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> suppliesList = new ArrayList<Model>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_supplies, container, false);

        //Back button to return to home/all listings page
        Button backBtn = view.findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RecyclerHomeFragment());
                fr.commit();
            }
        });

        //Sets up recycler view from fragment's layout
        recyclerView = view.findViewById(R.id.recycleView3);

        initializeList();

        setAdapter();

        return view;
    }

    //Sets up recycler view as grid w/ 2 columns & passes arraylist along with onClickListener to view individual listing info when listing is clicked
    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(suppliesList, listener);
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
                intent.putExtra("name", suppliesList.get(position).getName());
                intent.putExtra("price", suppliesList.get(position).getPrice());
                intent.putExtra("image", suppliesList.get(position).getImage());
                startActivity(intent);
            }
        };
    }

    //Sets up suppliesList's objects/supplies listings
    private void initializeList() {
        suppliesList.clear();

        Model red_pencils = new Model("$4", "Red Pencil Pack", R.drawable.red_pencils, "Supplies");
        suppliesList.add(red_pencils);

        Model ballpoint_pens = new Model("$8", "Ballpoint Pen Set", R.drawable.ballpoint_pens, "Supplies");
        suppliesList.add(ballpoint_pens);

        Model markers = new Model("$33", "Art Markers w/ Case", R.drawable.markers, "Supplies");
        suppliesList.add(markers);

        Model spiral_notebooks = new Model("$26", "Spiral Notebook Set", R.drawable.spiral_notebooks, "Supplies");
        suppliesList.add(spiral_notebooks);

        Model highlighters = new Model("$12", "Highlighter Pack", R.drawable.highlighters, "Supplies");
        suppliesList.add(highlighters);

        Model sticky_notes = new Model("$3", "Sticky Notes", R.drawable.sticky_notes, "Supplies");
        suppliesList.add(sticky_notes);

        Model index_cards = new Model("$4", "Index Cards", R.drawable.index_cards, "Supplies");
        suppliesList.add(index_cards);

        Model leather_roll = new Model("$38", "Green Leather Pouch", R.drawable.leather_roll, "Supplies");
        suppliesList.add(leather_roll);
    }
}
