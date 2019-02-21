package com.example.a073105.daggerdemo.architecture.viewmodel.activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class HomeViewModel extends AndroidViewModel {


    private MutableLiveData<String> userImgUrl = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getUserImgUrl() {
        return userImgUrl;
    }
}
