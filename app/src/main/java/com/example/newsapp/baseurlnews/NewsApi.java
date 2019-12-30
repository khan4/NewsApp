package com.example.newsapp.baseurlnews;

import com.example.newsapp.newspojo.News;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    @GET("everything?q=pakistan&apiKey=961b67413ef84511ab5b3c12c875d0ea")
    Flowable<News> getNewsData();
}
