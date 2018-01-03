package com.example.chikara.networkcommunication;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chikara on 1/3/18.
 */

public interface ApiInterface {
    @GET(KeyIds.GET_SPECIES)
    Call<String> getResult(@Query("page") String page);
}
