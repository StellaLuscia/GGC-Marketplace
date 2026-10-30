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

public class RecyclerHomeFragment extends Fragment{
    //Main fragment/home page to display all listings
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    public static ArrayList<Model> recyclingArrayList = new ArrayList<Model>();

    Button clothingBtn, textbooksBtn, suppliesBtn, elecBtn;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_recycler_home, container, false);

        //Sets up recycler view from fragment's layout
        recyclerView = view.findViewById(R.id.recycleView);

        initializeList();

        setAdapter();

        //Assigns category buttons
        clothingBtn = view.findViewById(R.id.clothingBtn);
        textbooksBtn = view.findViewById(R.id.textbooksBtn);
        suppliesBtn = view.findViewById(R.id.suppliesBtn);
        elecBtn = view.findViewById(R.id.elecBtn);

        //Clothing button redirects user to clothing fragment when clicked
        clothingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ClothingFragment());
                fr.commit();
            }
        });

        //Textbooks button redirects user to textbooks fragment when clicked
        textbooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new TextbooksFragment());
                fr.commit();
            }
        });

        //Supplies button redirects user to supplies fragment when clicked
        suppliesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new SuppliesFragment());
                fr.commit();
            }
        });

        //Electronics button redirects user to electronics fragment when clicked
        elecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ElectronicsFragment());
                fr.commit();
            }
        });

        return view;
    }

    //Sets up recycler view as grid w/ 2 columns & passes arraylist along with onClickListener to view individual listing info when listing is clicked
    private void setAdapter() {
        setOnClickListener();
        adapter = new RecyclerAdapter(recyclingArrayList, listener);
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
                Model selectedItem = adapter.getItem(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), ListingDetailsActivity.class);
                intent.putExtra("name", selectedItem.getName());
                intent.putExtra("price", selectedItem.getPrice());
                intent.putExtra("image", selectedItem.getImage());
                startActivity(intent);
            }
        };
    }

    // Shows listings that match the entered name or category.
    public void filterList(String searchText) {
        ArrayList<Model> filteredList = new ArrayList<Model>();
        String search = searchText.toLowerCase().trim();

        for (Model item : recyclingArrayList) {
            if (item.getName().toLowerCase().contains(search)
                    || item.getType().toLowerCase().contains(search)) {
                filteredList.add(item);
            }
        }

        adapter = new RecyclerAdapter(filteredList, listener);
        recyclerView.setAdapter(adapter);
    }

    //List of all the products - unorganized - category fragments used to organize them
    private void initializeList() {

        //Must clear list, otherwise listings will duplicate when switching b/w fragments
        recyclingArrayList.clear();

        Model patterned_blouse = new Model("$25", "Patterned Blouse", R.drawable.patterned_blouse, "Clothing");
        recyclingArrayList.add(patterned_blouse);

        Model macbook = new Model("$312", "MacBook Air 13-in", R.drawable.macbook, "Electronics");
        recyclingArrayList.add(macbook);

        Model big_data_txt = new Model("$38", "Big Data at Work", R.drawable.big_data_txt, "Textbooks");
        recyclingArrayList.add(big_data_txt);

        Model corduroy_buttonUp = new Model("$32", "Corduroy Button Up", R.drawable.corduroy_button_up, "Clothing");
        recyclingArrayList.add(corduroy_buttonUp);

        Model khaki_pants = new Model("$38", "Khaki Pants", R.drawable.khaki_pants, "Clothing");
        recyclingArrayList.add(khaki_pants);

        Model oxford_thesaurus = new Model("$16", "Oxford Thesaurus", R.drawable.oxford_thesaurus, "Textbooks");
        recyclingArrayList.add(oxford_thesaurus);

        Model pink_smartWatch = new Model("$115", "Pink Smartwatch", R.drawable.pink_smartwatch, "Electronics");
        recyclingArrayList.add(pink_smartWatch);

        Model red_pencils = new Model("$4", "Red Pencils Pack", R.drawable.red_pencils, "Supplies");
        recyclingArrayList.add(red_pencils);

        Model english_txt = new Model("$21", "English Grammar I", R.drawable.english_txt, "Textbooks");
        recyclingArrayList.add(english_txt);

        Model wool_coat = new Model("$64", "Beige Wool Coat", R.drawable.wool_coat, "Clothing");
        recyclingArrayList.add(wool_coat);

        Model wacom = new Model("$78", "Wacom Tablet", R.drawable.wacom, "Electronics");
        recyclingArrayList.add(wacom);

        Model law_txt = new Model("$47", "The Law", R.drawable.law_txt, "Textbooks");
        recyclingArrayList.add(law_txt);

        Model bali_txt = new Model("$31", "Bali", R.drawable.bali_txt, "Textbooks");
        recyclingArrayList.add(bali_txt);

        Model graphic_design_txt = new Model("$45", "Graphic Design I", R.drawable.graphic_design_txt, "Textbooks");
        recyclingArrayList.add(graphic_design_txt);

        Model scarf = new Model("$19", "Multicolor Knit Scarf", R.drawable.scarf, "Clothing");
        recyclingArrayList.add(scarf);

        Model black_headphones = new Model("$56", "Black BT Headphones", R.drawable.black_headphones, "Electronics");
        recyclingArrayList.add(black_headphones);

        Model french_txt = new Model("$32", "French III", R.drawable.french_txt, "Textbooks");
        recyclingArrayList.add(french_txt);

        Model geography_txt = new Model("$58", "Geography II", R.drawable.geography_txt, "Textbooks");
        recyclingArrayList.add(geography_txt);

        Model ipad = new Model("$127", "iPad 9th Gen", R.drawable.ipad, "Electronics");
        recyclingArrayList.add(ipad);

        Model ballpoint_pens = new Model("$8", "Ballpoint Pen Set", R.drawable.ballpoint_pens, "Supplies");
        recyclingArrayList.add(ballpoint_pens);

        Model markers = new Model("$33", "Art Markers w/ Case", R.drawable.markers, "Supplies");
        recyclingArrayList.add(markers);

        Model plaid_jacket = new Model("$40", "Multicolor Plaid Jacket", R.drawable.plaid_jacket, "Clothing");
        recyclingArrayList.add(plaid_jacket);

        Model spiral_notebooks = new Model("$26", "Spiral Notebook Set", R.drawable.spiral_notebooks, "Supplies");
        recyclingArrayList.add(spiral_notebooks);

        Model highlighters = new Model("$12", "Highlighter Pack", R.drawable.highlighters, "Supplies");
        recyclingArrayList.add(highlighters);

        Model sticky_notes = new Model("$3", "Sticky Notes", R.drawable.sticky_notes, "Supplies");
        recyclingArrayList.add(sticky_notes);

        Model power_bank = new Model("$31", "Power Bank 20k-mAh", R.drawable.power_bank, "Electronics");
        recyclingArrayList.add(power_bank);

        Model index_cards = new Model("$4", "Index Cards", R.drawable.index_cards, "Supplies");
        recyclingArrayList.add(index_cards);

        Model pink_blouse = new Model("$35", "Dusty Pink Blouse", R.drawable.pink_blouse, "Clothing");
        recyclingArrayList.add(pink_blouse);

        Model leather_roll = new Model("$38", "Green Leather Pouch", R.drawable.leather_roll, "Supplies");
        recyclingArrayList.add(leather_roll);

        Model calculator = new Model("$11", "Black Calculator", R.drawable.calculator, "Electronics");
        recyclingArrayList.add(calculator);

        Model leather_jacket = new Model("$56", "Brown Leather Jacket", R.drawable.leather_jacket, "Clothing");
        recyclingArrayList.add(leather_jacket);

        Model earbuds = new Model("$44", "BT Earbuds w/ Case", R.drawable.earbuds, "Electronics");
        recyclingArrayList.add(earbuds);
    }

}
