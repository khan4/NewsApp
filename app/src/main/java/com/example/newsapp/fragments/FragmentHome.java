package com.example.newsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.dimodule.ViewModelProviderFactory;
import com.example.newsapp.newspojo.Articles;
import com.example.newsapp.newspojo.News;
import com.example.newsapp.newsviewmodel.NewsViewModel;
import com.example.newsapp.recyclerview.RecyclerAdapter;
import com.example.newsapp.resource.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FragmentHome extends DaggerFragment {

    private static final String TAG = "FragmentHome";
    private NewsViewModel viewModel;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Toolbar toolbar;

    @Inject
    RecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);


        viewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(NewsViewModel.class);
        observeData();
        return view;
    }

    private void observeData() {

        viewModel.observeNewsData().observe(this, new Observer<Resource<News>>() {
            @Override
            public void onChanged(Resource<News> newsResource) {

                if (newsResource.status !=null){
                    switch (newsResource.status){
                        case LOADING:
                            break;
                        case SUCCESS:
                            List<Articles> articl = newsResource.data.getArticlesList();
                            adapter.initNewsList(articl);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                          //  adapter = new RecyclerAdapter(articl,getContext());
                            recyclerView.setAdapter(adapter);
                            break;
                        case ERROR:
                            break;
                    }
                }

            }
        });
    }



}
