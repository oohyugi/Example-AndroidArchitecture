package com.example.oohyugi.livedata;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oohyugi on 7/13/17.
 */

public class ApiClient {

    ApiService mService;

    public  ApiClient(){
        OkHttpClient client = new OkHttpClient.Builder().
                readTimeout(6000,TimeUnit.SECONDS)
                .writeTimeout(6000,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://yogip.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mService = retrofit.create(ApiService.class);
    }
    public ApiService getmService() {
        return mService;
    }
}


