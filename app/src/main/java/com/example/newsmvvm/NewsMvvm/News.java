package com.example.newsmvvm.NewsMvvm;

import android.os.Bundle;

import com.example.newsmvvm.NewsAdapter;
import com.example.newsmvvm.R;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.newsmvvm.API.ArticlesItem;
import com.example.newsmvvm.API.SourcesItem;
import com.example.newsmvvm.Base.BaseActivity;

import java.util.List;

public class News extends BaseActivity {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    List<ArticlesItem>articles;
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_news );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );
        //Initialize View Model
        newsViewModel = ViewModelProviders.of ( activity ).get ( NewsViewModel.class);
        initView ();
        iniRecyclerView ();
        newsViewModel.loadNewsSources ();
         sebScribeToLiveData ();

    }

    public void initView(){
        tabLayout = findViewById ( R.id.tablayout );
        recyclerView = findViewById ( R.id.recycleview );
    }

    public void sebScribeToLiveData(){

        newsViewModel.sources.observe ( this, new Observer<List<SourcesItem>> () {
            @Override
            public void onChanged(List<SourcesItem> sourcesItems) {
                setTabLayoutWithNewsSources ( sourcesItems );
            }
        } );

       newsViewModel.showLoading.observe ( this, new Observer<Boolean> () {
           @Override
           public void onChanged(Boolean show) {
               if (show){
                   showProgreesBar ( R.string.loading );
               }
               else hideProgressDialog ();

           }
       } );
       newsViewModel.articles.observe ( this, new Observer<List<ArticlesItem>> () {
           @Override
           public void onChanged(List<ArticlesItem> articlesItems) {
               newsAdapter.changeData ( articlesItems );
           }
       } );

        newsViewModel.alertMessage.observe ( this, new Observer<String> () {
            @Override
            public void onChanged(String messsage) {
              showMessage ( null,messsage,getString (R.string.ok));
            }
        } );
    }


    public void iniRecyclerView(){
        newsAdapter = new NewsAdapter ( null );
        recyclerView.setAdapter ( newsAdapter );
        recyclerView.setLayoutManager (new LinearLayoutManager ( activity ) );
    }




    private void setTabLayoutWithNewsSources(final List<SourcesItem> sources) {
        for (int i=0;i<sources.size ();i++){

            TabLayout.Tab  tab=tabLayout.newTab ();
            tab.setText ( sources.get ( i ).getName () );
            tab.setTag ( sources.get (i) );

            tabLayout.addTab ( tab );
        }

        tabLayout.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //int tabPos=tab.getPosition ();
                // loadNewsSourcesById (sources.get ( tabPos ).getId () );
                SourcesItem item =((SourcesItem) tab.getTag ());
                newsViewModel.loadNewsBySourcesId (item.getId ());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected ( tab );

            }
        } );
        tabLayout.getTabAt ( 0 ).select ();
    }





}
