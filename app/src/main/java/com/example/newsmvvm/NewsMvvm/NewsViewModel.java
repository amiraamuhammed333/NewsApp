package com.example.newsmvvm.NewsMvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.newsmvvm.API.APIManger;
import com.example.newsmvvm.API.ArticlesItem;
import com.example.newsmvvm.API.NewsResponse;
import com.example.newsmvvm.API.SourceResponse;
import com.example.newsmvvm.API.SourcesItem;
import com.example.newsmvvm.Constanrs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends AndroidViewModel {

    MutableLiveData<List<SourcesItem>>sources = new MutableLiveData<> () ;
    MutableLiveData<Boolean>showLoading = new MutableLiveData<> () ;
    MutableLiveData<List<ArticlesItem>>articles= new MutableLiveData<> () ;
    MutableLiveData<String>alertMessage= new MutableLiveData<> () ;




    public NewsViewModel(@NonNull Application application) {
        super ( application );
    }

    public void loadNewsSources(){
        showLoading.setValue ( true );
        APIManger.getApis ().getNewaSources ( Constanrs.API_KEY ,Constanrs.LANGuAGE)
                .enqueue ( new Callback<SourceResponse> () {
                    @Override
                    public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                        showLoading.setValue (false);

                        if (response.body ()!=null){
                            sources.postValue ( response.body ().getSources () );
                        }
                    }

                    @Override
                    public void onFailure(Call<SourceResponse> call, Throwable t) {

                        showLoading.setValue ( false );
                        alertMessage.setValue ( t.getLocalizedMessage () );

                    }
                } );


    }


    public void loadNewsBySourcesId(String sourceId){


        showLoading.setValue ( true );
        APIManger.getApis ().getNews ( Constanrs.API_KEY,Constanrs.LANGuAGE,sourceId )
                .enqueue ( new Callback<NewsResponse> () {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        showLoading.setValue ( false );

                        if (response.body ()!=null){
                        articles.setValue (response.body ().getArticles ()  );}
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                        showLoading.setValue ( false );
                        alertMessage.setValue ( t.getLocalizedMessage () );

                    }
                } );



    }
}
