package com.example.newsapp.di;

import com.example.newsapp.fragments.FragmentBusiness;
import com.example.newsapp.fragments.FragmentHome;
import com.example.newsapp.fragments.FragmentSports;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract FragmentHome contributeHomeFragment();

    @ContributesAndroidInjector
    abstract FragmentSports contributeSportsFragment();

    @ContributesAndroidInjector
    abstract FragmentBusiness contributeBusinessFragment();

}
