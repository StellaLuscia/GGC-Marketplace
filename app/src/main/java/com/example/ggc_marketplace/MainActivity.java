package com.example.ggc_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.helper.widget.Grid;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Sets default fragment as home fragment to display its layout b/c main activity layout is mostly blank to allow switching b/w fragments
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecyclerHomeFragment()).commit();

        //Redirects user to matching fragment based on icon clicked (ex: home icon -> home fragment)
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new RecyclerHomeFragment();
            } else if (item.getItemId() == R.id.wishlist) {
                selectedFragment = new WishlistFragment();
            } else if (item.getItemId() == R.id.cart) {
                selectedFragment = new CartFragment();
            } else if (item.getItemId() == R.id.account) {
                selectedFragment = new AccountFragment();
            }

            //Changes fragment used in main activity to selected fragment based on icon clicked
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }

    //Top Search Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search listings");

        // Filters the home listings while the user types.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterHomeListings(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterHomeListings(newText);
                return true;
            }
        });

        return true;
    }

    // Sends the search text to the home fragment when it is open.
    private void filterHomeListings(String searchText) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof RecyclerHomeFragment) {
            ((RecyclerHomeFragment) currentFragment).filterList(searchText);
        }
    }
}
