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

public class TextbooksFragment extends Fragment {
    private RecyclerView recyclerView;

    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> textbooksList = new ArrayList<Model>();
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_textbooks, container, false);

        //Back button to return to home/all listings page
        Button backBtn = view.findViewById(R.id.backBtn3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RecyclerHomeFragment());
                fr.commit();
            }
        });

        //Sets up recycler view from fragment's layout
        recyclerView = view.findViewById(R.id.recycleView4);

        initializeList();

        setAdapter();

        return view;
    }

    //Sets up recycler view as grid w/ 2 columns & passes arraylist along with onClickListener to view individual listing info when listing is clicked
    private void setAdapter() {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(textbooksList, listener);
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
                intent.putExtra("name", textbooksList.get(position).getName());
                intent.putExtra("price", textbooksList.get(position).getPrice());
                intent.putExtra("image", textbooksList.get(position).getImage());
                startActivity(intent);
            }
        };
    }

    //Sets up textbooksList's objects/textbook listings
    private void initializeList() {
        textbooksList.clear();

        Model english_txt = new Model("$21", "English Grammar I", R.drawable.english_txt, "Textbooks");
        textbooksList.add(english_txt);

        Model law_txt = new Model("$47", "The Law", R.drawable.law_txt, "Textbooks");
        textbooksList.add(law_txt);

        Model oxford_thesaurus = new Model("$16", "Oxford Thesaurus", R.drawable.oxford_thesaurus, "Textbooks");
        textbooksList.add(oxford_thesaurus);

        Model bali_txt = new Model("$31", "Bali", R.drawable.bali_txt, "Textbooks");
        textbooksList.add(bali_txt);

        Model big_data_txt = new Model("$38", "Big Data at Work", R.drawable.big_data_txt, "Textbooks");
        textbooksList.add(big_data_txt);

        Model graphic_design_txt = new Model("$45", "Graphic Design I", R.drawable.graphic_design_txt, "Textbooks");
        textbooksList.add(graphic_design_txt);

        Model french_txt = new Model("$32", "French III", R.drawable.french_txt, "Textbooks");
        textbooksList.add(french_txt);

        Model geography_txt = new Model("$58", "Geography II", R.drawable.geography_txt, "Textbooks");
        textbooksList.add(geography_txt);
    }
}
