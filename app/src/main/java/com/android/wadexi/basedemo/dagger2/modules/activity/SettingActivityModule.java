package com.android.wadexi.basedemo.dagger2.modules.activity;

import com.android.wadexi.basedemo.beans.Bean1;
import com.android.wadexi.basedemo.dagger2.annotation.ActivityScope;

import java.util.Random;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingActivityModule {

    @Provides
    @Named("SettingActivity_title")
    static String provideSettingActivityTitle(){
        return String.valueOf(new Random(System.currentTimeMillis()).nextInt());
    }


    @Provides
//    @ActivityScope 不加 -> 在同一activity中调用两次是不同的实例
    @ActivityScope //加 -> 在同一activity中调用两次是相同的实例
    @Named("SettingActivity_Bean")
    static Bean1 provideSettingActivityBean(){
        return new Bean1("22");
    }

}
