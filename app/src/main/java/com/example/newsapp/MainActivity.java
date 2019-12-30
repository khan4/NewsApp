package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.newsapp.baseurlnews.NewsApi;
import com.example.newsapp.constants.Constants;
import com.example.newsapp.dimodule.ViewModelProviderFactory;
import com.example.newsapp.fragments.FragmentBusiness;
import com.example.newsapp.fragments.FragmentHome;
import com.example.newsapp.fragments.FragmentSearch;
import com.example.newsapp.fragments.FragmentSports;
import com.example.newsapp.newspojo.Articles;
import com.example.newsapp.newspojo.News;
import com.example.newsapp.newsviewmodel.NewsViewModel;
import com.example.newsapp.resource.Resource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    FragmentHome fragmentHome;
    @Inject
    FragmentBusiness fragmentBusiness;
    @Inject
    FragmentSports fragmentSports;

    private static final String TAG = "MainActivity";

    private String[] nameList ={"Pakistan","India","Srilanka","Afghanistan","Australia","Zimbawabe","NewZeland",
            "England","WestIndies","Namibya","KhanGyro"};

    private HashSet<String> countriesName;

    private SearchView searchView;
    private SearchView searchCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countriesName = new HashSet<>();

        Locale[] locales =Locale.getAvailableLocales();

        for (Locale loc : locales){
            if (loc.getDisplayCountry()!=""){
                countriesName.add(loc.getDisplayCountry());
                Log.d(TAG, "onCreate: ");
            }
        }

        for (String hs : countriesName){
            Log.d(TAG, "Country Name = "+hs);

        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentHome())
                .commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.homeTab:
                    selectedFragment = fragmentHome;
                    break;
                case R.id.sports:
                    selectedFragment = fragmentSports;
                    break;
                case R.id.business:
                    selectedFragment = fragmentBusiness;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment)
                    .commit();
            return true;
        }
    };




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.actionSearch);
        MenuItem menuItem2 = menu.findItem(R.id.countrySearch);

        searchCountry =(SearchView) menuItem2.getActionView();
        searchCountry.setQueryHint("Search Any Country");


        searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type to Search");

        searchCountry.setImeOptions(R.drawable.home_background);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


}
