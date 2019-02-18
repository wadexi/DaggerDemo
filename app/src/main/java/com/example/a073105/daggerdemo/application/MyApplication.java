package com.example.a073105.daggerdemo.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.a073105.daggerdemo.beans.Bean1;
import com.example.a073105.daggerdemo.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {

    private static final String TAG = "MyApplication";//快捷键 logt

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    Bean1 bean1;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().build().inject(this);
        Log.d(TAG, "onCreate: bean1:" + bean1.toString());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerAppComponent.builder().build();
//    }
}
