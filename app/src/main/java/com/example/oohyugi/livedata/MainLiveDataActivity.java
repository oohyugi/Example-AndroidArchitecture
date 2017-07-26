package com.example.oohyugi.livedata;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.List;

public class MainLiveDataActivity extends LifecycleActivity {


    AdapterListAnimals mAdapterListAnimals;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mRefreshLayout;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        mProgressBar = findViewById(R.id.progress);
        mRefreshLayout = findViewById(R.id.swipe);
        loadData();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });


    }

    private void loadData() {

                mProgressBar.setVisibility(View.VISIBLE);
                final MainViewModel mainViewModel = ViewModelProviders.of(MainLiveDataActivity.this).get(MainViewModel.class);
                mainViewModel.getAnimals().observe(MainLiveDataActivity.this, new Observer<List<AnimalsDao>>() {
                    @Override
                    public void onChanged(@Nullable List<AnimalsDao> list) {
                        Log.e("onChanged: ", new Gson().toJson(list));
                        mAdapterListAnimals = new AdapterListAnimals(MainLiveDataActivity.this, list);
                        rv.setLayoutManager(new LinearLayoutManager(MainLiveDataActivity.this));
                        rv.setAdapter(mAdapterListAnimals);
                        mProgressBar.setVisibility(View.GONE);
                        mRefreshLayout.setRefreshing(false);
                    }
                });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
