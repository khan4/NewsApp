package com.example.newsapp.di;

import com.example.newsapp.MainActivity;
import com.example.newsapp.dimodule.NewsModule;
import com.example.newsapp.newsviewmodel.NewsViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {FragmentBuilderModule.class,NewsViewModelModule.class, NewsModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
