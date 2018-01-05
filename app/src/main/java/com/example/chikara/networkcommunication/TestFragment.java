package com.example.chikara.networkcommunication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import retrofit2.Call;

/**
 * Created by chikara on 1/3/18.
 */

public class TestFragment extends Fragment {

    Call<String> mGetResultCallObj;
    String param = "1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        serverCommunication();
    }

    private void serverCommunication() {
        mGetResultCallObj = RetrofitClient.getAPI(getActivity()).getResult("1");
        mGetResultCallObj.enqueue(new RetrofitConnectionCallBack(getActivity(),
                new RetrofitConnectionCallBack.ConnectionListener() {
                    @Override
                    public void onSuccess(JSONObject respObj) {
                        Log.e("respObj", "" + respObj);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("errorMessage", "" + errorMessage);
                    }
                }));
    }

}
