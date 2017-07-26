package com.example.oohyugi.livedata;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity1 extends AppCompatActivity {


    AdapterListAnimals mAdapterListAnimals;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mRefreshLayout;
    RecyclerView rv;
    List<AnimalsDao> listAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        mProgressBar = findViewById(R.id.progress);
        mRefreshLayout = findViewById(R.id.swipe);
        loadDataFromApi();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataFromApi();
            }
        });


    }

    private void loadDataFromApi() {
        mProgressBar.setVisibility(View.VISIBLE);

                ApiClient apiClient = new ApiClient();
                apiClient.getmService().getData().enqueue(new Callback<List<AnimalsDao>>() {
                    @Override
                    public void onResponse(Call<List<AnimalsDao>> call, Response<List<AnimalsDao>> response) {
                        Log.e("onResponse: ", new Gson().toJson(response.body()));
                        mAdapterListAnimals = new AdapterListAnimals(MainActivity1.this, response.body());
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity1.this));
                        rv.setAdapter(mAdapterListAnimals);
                        mProgressBar.setVisibility(View.GONE);
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<AnimalsDao>> call, Throwable t) {
                        Log.e("onFailure: ", t.getMessage());

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
