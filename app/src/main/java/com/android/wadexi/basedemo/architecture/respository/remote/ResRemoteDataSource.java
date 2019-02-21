package com.android.wadexi.basedemo.architecture.respository.remote;

import android.util.Log;

import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.beans.ResponBean;
import com.android.wadexi.basedemo.webservice.HttpService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ResRemoteDataSource {

    private static final String TAG = "ResRemoteDataSource";
    private final HttpService httpService;


    @Inject
    public ResRemoteDataSource(HttpService httpService) {
        this.httpService = httpService;
    }

    public void getCooKMenu(String menu, int num,Callback<CookBookBean> callback){
        Call<CookBookBean> call = httpService.getCookMenu(menu, num, "cb5c01ff3ee0eeada0d4069ef38b78f4");
        call.enqueue(callback);
    }
}
