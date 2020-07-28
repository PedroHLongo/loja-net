package com.example.lojanet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.lojanet.ui.account.AccountFragment;
import com.example.lojanet.ui.home.HomeFragment;
import com.example.lojanet.ui.products.ProductsFragment;
import com.example.lojanet.ui.wishlist.WishlistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsBottomNavigation();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.viewPager,new HomeFragment()).commit();

    }

    private void settingsBottomNavigation(){

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomnavigation);

        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);

        navigation(bottomNavigationViewEx);

    }

    private  void navigation(BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){

                    case R.id.navigation_home:
                        fragmentTransaction.replace(R.id.viewPager,new HomeFragment()).commit();
                        return true;

                    case R.id.navigation_products:
                        fragmentTransaction.replace(R.id.viewPager,new ProductsFragment()).commit();
                        return true;

                    case R.id.navigation_wishlist:
                        fragmentTransaction.replace(R.id.viewPager,new WishlistFragment()).commit();
                        return true;

                    case R.id.navigation_account:
                        fragmentTransaction.replace(R.id.viewPager,new AccountFragment()).commit();
                        return true;

                }

                return false;
            }
        });

    }

}