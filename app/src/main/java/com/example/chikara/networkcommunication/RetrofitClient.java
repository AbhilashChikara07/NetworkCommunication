package com.example.chikara.networkcommunication;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by chikara on 1/3/18.
 */

public class RetrofitClient {

    private static final int MAX_REQUESTS = 4;
    private static final int CONNECTION_TIMEOUT = 60;// time in seconds
    private static final int READ_TIMEOUT = 60;// time in seconds
    private static final int WRITE_TIMEOUT = 60;// time in seconds
    private static RetrofitClient mRetrofitClient;
    private static Retrofit mRetrofitObj;
    private static String BASE_URL = "";

    static ApiInterface getAPI(Context context) {
        if (mRetrofitClient == null) {
            mRetrofitClient = new RetrofitClient();

            BASE_URL = context.getString(R.string.server_uri);
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(MAX_REQUESTS);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            mRetrofitObj = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    // converter sequence does matter , a lot !
                    .addConverterFactory(ScalarsConverterFactory.create())//for response to string conversion
                    .addConverterFactory(GsonConverterFactory.create())  //for request to json conversion
                    .build();
        }
        return mRetrofitObj.create(ApiInterface.class);
    }
}

