package com.example.oohyugi.livedata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by oohyugi on 7/13/17.
 */

public interface ApiService {

    @GET("listanimals.json")
    Call<List<AnimalsDao>> getData();
}
