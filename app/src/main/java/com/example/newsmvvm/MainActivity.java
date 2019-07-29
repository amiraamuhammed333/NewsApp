package com.example.newsmvvm;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsmvvm.NewsMvvm.News;

import static com.example.newsmvvm.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        new Handler (  )
                .postDelayed ( new Runnable () {
                    @Override
                    public void run() {

                        startActivity ( new Intent ( MainActivity.this,News.class ) );
                    }
                },2000 );
    }
}
