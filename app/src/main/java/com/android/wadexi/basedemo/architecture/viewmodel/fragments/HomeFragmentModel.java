package com.android.wadexi.basedemo.architecture.viewmodel.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.wadexi.basedemo.architecture.respository.contentprovider.ResDataSource;
import com.android.wadexi.basedemo.architecture.respository.remote.ResRemoteDataSource;
import com.android.wadexi.basedemo.beans.ContactData;
import com.android.wadexi.basedemo.beans.CookBookBean;
import com.android.wadexi.basedemo.beans.ResponBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HomeFragmentModel extends AndroidViewModel {

    private static final String TAG = "ConstantFragmentModel";
    private MutableLiveData<CookBookBean> mutableLiveData = new MutableLiveData<>();

    private Application application;



    ResRemoteDataSource dataSource;

    @Inject
    public HomeFragmentModel(@NonNull Application application, ResRemoteDataSource dataSource) {
        super(application);
        this.application = application;
        this.dataSource = dataSource;
    }

    //    @Inject
//    public ConstantFragmentModel(Application application,ResDataSource dataSource) {
//        this.application = application;
//        this.dataSource = dataSource;
//    }

    //    public ConstantFragmentModel(@NonNull Application application) {
//        super(application);
//        this.application = application;
//    }


    public MutableLiveData<CookBookBean> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * 获取聚合数据的菜谱
     */
    public void getCookMenu(String menu, int num) {
        dataSource.getCooKMenu(menu,num,new Callback<CookBookBean>() {
            @Override
            public void onResponse(Call<CookBookBean> call, Response<CookBookBean> response) {
                if(response.isSuccessful()){
                    CookBookBean responBean = response.body();
                    Log.d(TAG, "onResponse: " + responBean.toString());
                    mutableLiveData.setValue(responBean);
                }else {
                    Log.d(TAG, "onResponse: code" + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CookBookBean> call, Throwable t) {
                Log.w(TAG, "onFailure: ",t );
            }
        });

    }



}
