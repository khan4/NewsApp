package com.example.newsapp.newsviewmodel;

import androidx.lifecycle.ViewModel;

import com.example.newsapp.dimodule.ViewModelKeys;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKeys(NewsViewModel.class)
    public abstract ViewModel bindNewsViewModel(NewsViewModel newsViewModel);


}
