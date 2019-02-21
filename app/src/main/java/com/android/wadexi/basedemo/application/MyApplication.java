package com.android.wadexi.basedemo.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.android.wadexi.basedemo.beans.Bean1;
import com.android.wadexi.basedemo.dagger2.components.DaggerAppComponent;

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
        DaggerAppComponent.builder().create(this).inject(this);
//        DaggerAppComponent.builder().application(this).build().inject(this);
        Log.d(TAG, "onCreate: bean1:" + bean1.toString());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
