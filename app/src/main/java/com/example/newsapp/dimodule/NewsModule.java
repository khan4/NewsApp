package com.example.newsapp.dimodule;


import com.example.newsapp.baseurlnews.NewsApi;
import com.example.newsapp.fragments.FragmentBusiness;
import com.example.newsapp.fragments.FragmentHome;
import com.example.newsapp.fragments.FragmentSports;
import com.example.newsapp.recyclerview.RecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NewsModule {

    @Provides
    static NewsApi providesNewsApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }

    @Provides
    static RecyclerAdapter providesRecyclerAdapter(){
        return new RecyclerAdapter();
    }
    @Provides
    static FragmentHome providesHomeFragment(){
        return new FragmentHome();
    }
    @Provides
    static FragmentBusiness provideFragmentBusiness(){
        return new FragmentBusiness();
    }
    @Provides
    static FragmentSports providesFragmentSports(){
        return new FragmentSports();
    }


}
