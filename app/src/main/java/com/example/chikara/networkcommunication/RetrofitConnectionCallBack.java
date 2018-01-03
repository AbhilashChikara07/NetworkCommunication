package com.example.chikara.networkcommunication;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chikara on 1/3/18.
 */

public class RetrofitConnectionCallBack implements Callback<String> {
    private Context context;
    private ConnectionListener listener;

    RetrofitConnectionCallBack(Context context, ConnectionListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.e("response", "" + response);
        try{
            JSONObject jsonObject = new JSONObject(response.body().toString());
            Log.e("jsonObject", "" + jsonObject);
        }catch (Exception e){

        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.e("response-t", "" + t);
    }

    interface ConnectionListener {
        void onSuccess(String resp);

        void onError(String errorMessage);
    }

}
