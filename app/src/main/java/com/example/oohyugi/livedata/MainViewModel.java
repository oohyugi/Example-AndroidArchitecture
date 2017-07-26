package com.example.oohyugi.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by oohyugi on 7/13/17.
 */

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<AnimalsDao>> mlistAnimals= new MutableLiveData<>();
    LiveData<List<AnimalsDao>> getAnimals(){

            loadDataFromApi();

            return mlistAnimals;

    }
    private void loadDataFromApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.getmService().getData().enqueue(new Callback<List<AnimalsDao>>() {
            @Override
            public void onResponse(Call<List<AnimalsDao>> call, Response<List<AnimalsDao>> response) {
                Log.e("onResponse: ", new Gson().toJson(response.body()));
                mlistAnimals.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AnimalsDao>> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());

            }
        });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e( "onCleared: ","called" );
    }
}
