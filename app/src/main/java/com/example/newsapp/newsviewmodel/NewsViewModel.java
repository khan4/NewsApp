package com.example.newsapp.newsviewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.baseurlnews.NewsApi;
import com.example.newsapp.newspojo.Articles;
import com.example.newsapp.newspojo.News;
import com.example.newsapp.resource.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    private static final String TAG = "NewsViewModel";
    private NewsApi newsApi;
    private MediatorLiveData<Resource<News>> newsData;


    @Inject
    public NewsViewModel(NewsApi newsApi){
        this.newsApi = newsApi;
    }

    public LiveData<Resource<News>> observeNewsData(){

        if (newsData == null){

            newsData = new MediatorLiveData<>();
            newsData.setValue(Resource.loading((News)null));

            final LiveData<Resource<News>> source = LiveDataReactiveStreams.fromPublisher(
                    newsApi.getNewsData()
                    .onErrorReturn(new Function<Throwable, News>() {
                        @Override
                        public News apply(Throwable throwable) throws Exception {
                            News news = new News();
                            news.setId(-1);
                            return news;
                        }
                    })
                    .map(new Function<News, Resource<News>>() {
                        @Override
                        public Resource<News> apply(News news) throws Exception {

                            if (news.getId() == -1){
                                return Resource.error("There is error while loading ",(News) null);
                            }
                            return Resource.success(news);
                        }
                    })
                    .subscribeOn(Schedulers.io())

            );

            newsData.addSource(source, new Observer<Resource<News>>() {
                @Override
                public void onChanged(Resource<News> newsResource) {
                    newsData.setValue(newsResource);
                    newsData.removeSource(source);
                }
            });
        }
        return newsData;

    }


}
