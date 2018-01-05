package com.example.chikara.networkcommunication;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.net.UnknownHostException;

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
        try {
            listener.onSuccess(new JSONObject(response.body()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (t instanceof UnknownHostException) {
            listener.onError("No Internet");
        } else if (t instanceof RetrofitExceptionClass) {
            t.printStackTrace();
            listener.onError(t.getMessage());
        } else {
            listener.onError("Some thing went wrong");
        }
    }

    interface ConnectionListener {
        void onSuccess(JSONObject respObj);

        void onError(String errorMessage);
    }

}
